package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.repository.ComentarioRepository;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.BuscarTodosPorComentarioReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.BuscarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.CriarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.repository.ReacaoComentarioRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoComentarioService {
    private final ReacaoComentarioRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;

    @Transactional
    public void criar(@Valid CriarReacaoComentarioCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Comentario comentario = comentarioRepository.getById(cmd.getIdComentario());

        ReacaoComentario reacaoExistente = repository.findByIdUsuarioAndIdComentario(usuario, comentario);
        if (reacaoExistente == null) {
            ReacaoComentario reacao = new ReacaoComentario();
            reacao.setIdComentario(comentario);
            reacao.setIdUsuario(usuario);
            reacao.setCurtida(cmd.getCurtida());
            repository.save(reacao);
        } else if (reacaoExistente.isCurtida() == cmd.getCurtida()) {
            repository.deleteByIdUsuarioAndIdComentario(usuario, comentario);
        } else {
            reacaoExistente.setCurtida(!reacaoExistente.isCurtida());
            repository.save(reacaoExistente);
        }
    }

    public ReacaoComentario buscarUm(@Valid BuscarUmReacaoComentarioCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Comentario comentario = comentarioRepository.getById(cmd.getIdComentario());

        ReacaoComentario reacaoComentario = repository.findByIdUsuarioAndIdComentario(usuario, comentario);

        if (reacaoComentario == null) {
            throw new NaoEncontradoException("Reação do comentário não encontrado!");
        }

        return reacaoComentario;
    }

    public List<ReacaoComentario> buscarTodosPorComentario(@Valid BuscarTodosPorComentarioReacaoComentarioCommand cmd) {
        Comentario comentario = comentarioRepository.getById(cmd.getIdComentario());
        return repository.findAllByIdComentario(comentario);
    }
}
