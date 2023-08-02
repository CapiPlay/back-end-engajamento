package br.senai.sc.engajamento;

import br.senai.sc.engajamento.comentario.model.command.BuscarUmComentarioCommand;
import br.senai.sc.engajamento.comentario.model.command.CriarComentarioCommand;
import br.senai.sc.engajamento.comentario.model.command.DeletarComentarioCommand;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.service.ComentarioService;
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
public class ComentarioTest {
    private ComentarioService comentarioService;
    private UsuarioService usuarioService;
    private VideoService videoService;
    private Comentario comentario;
    private Usuario usuario1;
    private Usuario usuario2;
    private Video video;

    @BeforeEach
    public void setup() {
        usuarioService.criar(usuario);
        usuarioService.criar(usuario2);
        videoService.criar(video);
        comentario = comentarioService.criar(new CriarComentarioCommand(usuario1.getIdUsuario(), video.getIdVideo(), "teste1"));
    }

    @Test
    public void deletar() {
        comentarioService.deletar(new DeletarComentarioCommand(comentario.getIdComentario(), usuario2.getIdUsuario()));
        assertEquals(comentario, comentarioService.buscarUm(new BuscarUmComentarioCommand(comentario.getIdComentario())));
        comentarioService.deletar(new DeletarComentarioCommand(comentario.getIdComentario(), usuario1.getIdUsuario()));
        assertNull(comentarioService.buscarUm(new BuscarUmComentarioCommand(comentario.getIdComentario())));
    }
}
