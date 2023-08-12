package br.senai.sc.engajamento.resposta.service;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.repository.ComentarioRepository;
import br.senai.sc.engajamento.exception.AcaoNaoPermitidaException;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.resposta.model.command.BuscarTodosPorComentarioRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.BuscarUmaRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.CriarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.DeletarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.repository.RespostaRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import br.senai.sc.engajamento.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RespostaService {
    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;
    private final VideoRepository videoRepository;
    private final VideoService videoService;

    public Resposta criar(@Valid CriarRespostaCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Comentario comentario = comentarioRepository.getById(cmd.getIdComentario());
        Video video = videoRepository.getById(comentario.getIdVideo().getId());

        if (!video.getEhInativado()) {
            Resposta resposta = new Resposta(
                    cmd.getTexto(),
                    usuario,
                    comentario
            );

            comentario.setQtdRespostas(comentario.getQtdRespostas() + 1);
            comentarioRepository.save(comentario);

            video.setQtdRespostas(video.getQtdRespostas() + 1);
            videoService.editarPontuacao(video);

            return respostaRepository.save(resposta);
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public Resposta buscarUm(@Valid BuscarUmaRespostaCommand cmd) {
        Resposta resposta = respostaRepository.getById(cmd.getIdResposta());
        Video video = videoRepository.getById(resposta.getIdComentario().getIdVideo().getId());

        if (!video.getEhInativado()) {
            return retornaResposta(cmd.getIdResposta());
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public List<Resposta> buscarTodosPorComentario(@Valid BuscarTodosPorComentarioRespostaCommand cmd) {
        Comentario comentario = comentarioRepository.getById(cmd.getIdComentario());
        Video video = videoRepository.getById(comentario.getIdVideo().getId());

        if (!video.getEhInativado()) {
            return respostaRepository.findAllByIdComentario(comentarioRepository.findById(cmd.getIdComentario())
                    .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado")));
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public void deletar(@Valid DeletarRespostaCommand cmd) {
        Resposta resposta = retornaResposta(cmd.getIdResposta());
        Comentario comentario = comentarioRepository.getById(resposta.getIdComentario().getIdComentario());
        Video video = videoRepository.getById(comentario.getIdVideo().getId());

        if (!video.getEhInativado()) {
            if (!(cmd.getIdUsuario().equals(resposta.getIdUsuario().getIdUsuario()))) {
                throw new AcaoNaoPermitidaException();
            }

            video.setQtdRespostas(video.getQtdRespostas() - 1);
            videoService.editarPontuacao(video);

            comentario.setQtdRespostas(comentario.getQtdRespostas() - 1);
            comentarioRepository.save(comentario);

            respostaRepository.delete(resposta);
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    private Resposta retornaResposta(String idResposta) {
        Optional<Resposta> resposta = respostaRepository.findById(idResposta);
        if (resposta.isPresent()) {
            return resposta.get();
        }
        throw new NaoEncontradoException("Resposta não encontrada");
    }
}