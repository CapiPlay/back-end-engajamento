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

    public void criar(@Valid CriarReacaoComentarioCommand cmd) {
        Usuario usuario = usuarioRepository.findById(cmd.getIdUsuario())
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!"));
        Comentario comentario = comentarioRepository.findById(cmd.getIdComentario())
                .orElseThrow(()-> new NaoEncontradoException("Comentário não encontrado!"));

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
        Usuario usuario = usuarioRepository.findById(cmd.getIdUsuario())
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!"));
        Comentario comentario = comentarioRepository.findById(cmd.getIdComentario())
                .orElseThrow(()-> new NaoEncontradoException("Comentário não encontrado!"));

        ReacaoComentario reacaoComentario = repository.findByIdUsuarioAndIdComentario(usuario, comentario);

        if (reacaoComentario == null) {
            throw new NaoEncontradoException("Reação do comentário não encontrado!");
        }

        return reacaoComentario;
    }

    public List<ReacaoComentario> buscarTodosPorComentario(@Valid BuscarTodosPorComentarioReacaoComentarioCommand cmd) {
        Comentario comentario = comentarioRepository.findById(cmd.getIdComentario())
                .orElseThrow(()-> new NaoEncontradoException("Comentário não encontrado!"));
        return repository.findAllByIdComentario(comentario);
    }
}
