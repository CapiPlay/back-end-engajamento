package br.senai.sc.engajamento.comentario.service;

import br.senai.sc.engajamento.comentario.model.command.*;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.repository.ComentarioRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Data
public class ComentarioService {

    private ComentarioRepository comentarioRepository;
    private UsuarioService usuarioService;
    private VideoService videoService;

    public Comentario criar(
            CriarComentarioCommand cmd
    ) {
        Video video = videoService.retornaVideo(cmd.getIdVideo());
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        Comentario comentario = new Comentario(
                cmd.getTexto(),
                usuario,
                video
        );
        return comentarioRepository.save(comentario);
    }

    public Comentario buscarUm(
            BuscarUmComentarioCommand cmd
    ) {
        return retornaComentario(cmd.getIdComentario());
    }

    public List<Comentario> buscarTodosPorVideo(
            BuscarTodosPorVideoComentarioCommand cmd
    ) {
        return comentarioRepository.findAllByVideo(videoService.buscarUm(cmd.getIdVideo()));
    }

    public void adicionarResposta(
            AdicionarRespostaComentarioCommand cmd
    ) {
        Comentario comentario = retornaComentario(cmd.getIdComentario());
        comentario.setQtdRespostas(
                comentario.getQtdRespostas() + 1
        );
    }

    public void deletar(
            DeletarComentarioCommand cmd
    ) {
        Comentario comentario = retornaComentario(cmd.getIdComentario());
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        if (usuario.getId() != comentario.getIdResposta()) {
            /*MUDAR - criar nossa exception*/
            throw new IllegalArgumentException();
        } else {
            comentarioRepository.delete(comentario);
        }
    }

    public Comentario retornaComentario(UUID idComentario) {
        Optional<Comentario> optional = comentarioRepository.findById(idComentario);
        if (optional.isPresent()) {
            return optional.get();
        }
        /*MUDAR - criar exeption*/
        throw new IllegalArgumentException();
    }

}