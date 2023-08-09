package br.senai.sc.engajamento;

import br.senai.sc.engajamento.comentario.model.command.CriarComentarioCommand;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.service.ComentarioService;
import br.senai.sc.engajamento.resposta.model.command.BuscarUmaRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.CriarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.DeletarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.service.RespostaService;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.service.VideoService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@AllArgsConstructor
public class RespostaTest {
    private RespostaService respostaService;
    private UsuarioService usuarioService;
    private ComentarioService comentarioService;
    private VideoService videoService;
    private Resposta resposta;
    private Usuario usuario1;
    private Usuario usuario2;
    private Comentario comentario;
    private Video video;

//    @BeforeEach
//    public void setup() {
//        usuarioService.criar(usuario1);
//        usuarioService.criar(usuario2);
//        videoService.criar(video);
//        comentario = comentarioService.criar(new CriarComentarioCommand(usuario1.getIdUsuario(), video.getId(), "teste1"));
//        resposta = respostaService.criar(new CriarRespostaCommand(usuario1.getIdUsuario(), comentario.getIdComentario(), "teste2"));
//    }
//
//    @Test
//    public void deletar() {
//        respostaService.deletar(new DeletarRespostaCommand(resposta.getIdResposta(), usuario2.getIdUsuario()));
//        assertEquals(resposta, respostaService.buscarUm(new BuscarUmaRespostaCommand(resposta.getIdResposta())));
//        respostaService.deletar(new DeletarRespostaCommand(resposta.getIdResposta(), usuario1.getIdUsuario()));
//        assertNull(respostaService.buscarUm(new BuscarUmaRespostaCommand(resposta.getIdResposta())));
//    }
}
