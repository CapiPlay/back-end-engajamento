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
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class RespostaService {

    private RespostaRepository respostaRepository;
    private UsuarioService usuarioService;
    private ComentarioService comentarioService;
    private VideoService videoService;

    public Resposta criar(
            CriarRespostaCommand cmd
    ) {
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        Comentario comentario = comentarioService.retornaComentario(cmd.getIdComentario());
        Video video = videoService.retornaVideo(comentario.getIdVideo().getId());
        Resposta resposta = new Resposta(
                cmd.getTexto(),
                usuario,
                comentario
        );
        comentario.setQtdRespostas(comentario.getQtdRespostas() + 1);


        video.setQtdRespostas(video.getQtdRespostas() + 1);



        videoService.editarPontuacao(video);

        return respostaRepository.save(resposta);
    }

    public Resposta buscarUm(
            BuscarUmaRespostaCommand cmd
    ) {
        return retornaResposta(cmd.getIdResposta());
    }

    public List<Resposta> buscarTodosPorComentario(
            BuscarTodosPorComentarioRespostaCommand cmd
    ) {
        BuscarUmComentarioCommand comentario = new BuscarUmComentarioCommand(cmd.getIdComentario());
        return respostaRepository.findAllByIdComentario(comentarioService.buscarUm(comentario));
    }

    public void deletar(
            DeletarRespostaCommand cmd
    ) {
        try {
            Resposta resposta = retornaResposta(cmd.getIdResposta());
            Comentario comentario = comentarioService.retornaComentario(resposta.getIdComentario().getIdComentario());
            Video video = videoService.retornaVideo(comentario.getIdVideo().getId());

            if (!(cmd.getIdUsuario().equals(resposta.getIdUsuario().getIdUsuario()))) {
                throw new AcaoNaoPermitidaException();
            }

            video.setQtdRespostas(video.getQtdRespostas() + 1);
            videoService.editarPontuacao(video);

            respostaRepository.delete(resposta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Resposta retornaResposta(String idResposta) {
        Optional<Resposta> resposta = respostaRepository.findById(idResposta);
        if (resposta.isPresent()) {
            return resposta.get();
        }
        throw new NaoEncontradoException("Resposta não encontrada!");
    }

}