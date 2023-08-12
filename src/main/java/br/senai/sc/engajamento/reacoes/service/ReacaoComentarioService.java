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
import br.senai.sc.engajamento.video.model.entity.Video;
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
        Video video = comentario.getIdVideo();

        if (!video.getEhInativado()) {
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
        } else {
            throw new NaoEncontradoException("Vídeo não encontrado");
        }
    }

    public ReacaoComentario buscarUm(@Valid BuscarUmReacaoComentarioCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Comentario comentario = comentarioRepository.getById(cmd.getIdComentario());
        Video video = comentario.getIdVideo();

        if (!video.getEhInativado()) {
            ReacaoComentario reacaoComentario = repository.findByIdUsuarioAndIdComentario(usuario, comentario);

            if (reacaoComentario == null) {
                throw new NaoEncontradoException("Reação do comentário não encontrado!");
            }
            return reacaoComentario;
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public List<ReacaoComentario> buscarTodosPorComentario(@Valid BuscarTodosPorComentarioReacaoComentarioCommand cmd) {
        Comentario comentario = comentarioRepository.getById(cmd.getIdComentario());
        Video video = comentario.getIdVideo();

        if (!video.getEhInativado()) {
            return repository.findAllByIdComentario(comentario);
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }
}
