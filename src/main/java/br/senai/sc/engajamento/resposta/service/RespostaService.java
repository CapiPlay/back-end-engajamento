package br.senai.sc.engajamento.resposta.service;

import br.senai.sc.engajamento.comentario.model.command.BuscarUmComentarioCommand;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.repository.ComentarioRepository;
import br.senai.sc.engajamento.comentario.service.ComentarioService;
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
import br.senai.sc.engajamento.usuario.service.UsuarioService;
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

        Resposta resposta = new Resposta(
                cmd.getTexto(),
                usuario,
                comentario
        );

        editarInformacoesVideo(resposta);
        return respostaRepository.save(resposta);
    }

    public Resposta buscarUm(@Valid BuscarUmaRespostaCommand cmd) {
        return retornaResposta(cmd.getIdResposta());
    }

    public List<Resposta> buscarTodosPorComentario(@Valid BuscarTodosPorComentarioRespostaCommand cmd) {
        return respostaRepository.findAllByIdComentario(comentarioRepository.findById(cmd.getIdComentario())
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrada!")));
    }

    public void deletar(@Valid DeletarRespostaCommand cmd) {
        Resposta resposta = retornaResposta(cmd.getIdResposta());
        if (!(cmd.getIdUsuario().equals(resposta.getIdUsuario().getIdUsuario()))) {
            throw new AcaoNaoPermitidaException();
        }
        editarInformacoesVideo(resposta);
        respostaRepository.delete(resposta);
    }

    private void editarInformacoesVideo(Resposta resposta) {
        Comentario comentario = comentarioRepository.getById(resposta.getIdComentario().getIdComentario());
        Video video = videoRepository.getById(comentario.getIdVideo().getId());

        video.setQtdRespostas(video.getQtdRespostas() + 1);
        videoService.editarPontuacao(video);
    }

    private Resposta retornaResposta(String idResposta) {
        Optional<Resposta> resposta = respostaRepository.findById(idResposta);
        if (resposta.isPresent()) {
            return resposta.get();
        }
        throw new NaoEncontradoException("Resposta não encontrada!");
    }

}