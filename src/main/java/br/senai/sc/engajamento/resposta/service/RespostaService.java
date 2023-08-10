package br.senai.sc.engajamento.resposta.service;

import br.senai.sc.engajamento.comentario.model.command.BuscarUmComentarioCommand;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
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
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RespostaService {
    private final RespostaRepository respostaRepository;
    private final UsuarioService usuarioService;
    private final ComentarioService comentarioService;
    private final VideoService videoService;

    public Resposta criar(@Valid CriarRespostaCommand cmd) {
        Usuario usuario = usuarioService.findById(cmd.getIdUsuario())
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrada!"));

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
        BuscarUmComentarioCommand comentario = new BuscarUmComentarioCommand(cmd.getIdComentario());
        return respostaRepository.findAllByIdComentario(comentarioService.buscarUm(comentario));
    }

    public void deletar(@Valid DeletarRespostaCommand cmd) {
        Resposta resposta = retornaResposta(cmd.getIdResposta());
        // TODO
        // cmd.getIdUsuario().equals(resposta.getIdUsuario().getIdUsuario()
        //         .orElseThrow(() -> new AcaoNaoPermitidaException());
        if (!(cmd.getIdUsuario().equals(resposta.getIdUsuario().getIdUsuario()))) {
            throw new AcaoNaoPermitidaException();
        }
        editarInformacoesVideo(resposta);
        respostaRepository.delete(resposta);
    }
 c
    private void editarInformacoesVideo(Resposta resposta) {
        Comentario comentario = comentarioService.findById(resposta.getIdComentario().getIdComentario())
                .orElseThrow(() -> new NaoEncontradoException("Comentário não encontrada!"));
        Video video = videoService.findById(comentario.getIdVideo().getId())
                .orElseThrow(() -> new NaoEncontradoException("Comentário não encontrada!"));

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