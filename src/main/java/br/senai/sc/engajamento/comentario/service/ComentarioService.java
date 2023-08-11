package br.senai.sc.engajamento.comentario.service;

import br.senai.sc.engajamento.comentario.model.command.*;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.repository.ComentarioRepository;
import br.senai.sc.engajamento.exception.AcaoNaoPermitidaException;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import br.senai.sc.engajamento.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ComentarioService {

    private ComentarioRepository comentarioRepository;
    private UsuarioRepository usuarioRepository;
    private VideoRepository videoRepository;
    private VideoService videoService;


    public Comentario criar(@Valid CriarComentarioCommand cmd) {
        Video video = videoRepository.getById(cmd.getIdVideo());
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Comentario comentario = new Comentario(
                cmd.getTexto(),
                usuario,
                video
        );
        video.setQtdComentarios(video.getQtdComentarios() + 1);
        videoService.editarPontuacao(video);
        return comentarioRepository.save(comentario);
    }

    public Comentario buscarUm(@Valid BuscarUmComentarioCommand cmd) {
        return retornaComentario(cmd.getIdComentario());
    }

    public List<Comentario> buscarTodosPorVideo(@Valid BuscarTodosPorVideoComentarioCommand cmd) {
        Video video = videoRepository.getById(cmd.getIdVideo());
        List<Comentario> list = comentarioRepository.findAllByIdVideo(video);
        list.sort(Comparator.comparing(Comentario::getDataHora).reversed());
        return list;
    }

    public List<Comentario> buscarTodosPorData(@Valid BuscarTodosPorDataComentarioCommand cmd)  {
        List<Comentario> listaComentariosFiltrados = new ArrayList<>();
        Video video = videoRepository.getById(cmd.getIdVideo());

        List<Comentario> listaComentarios =
                comentarioRepository.findAllByIdVideo(video);
        for (Comentario comentario : listaComentarios) {
            if (comentario.getDataHora().toLocalDate().equals(cmd.getData())) {
                listaComentariosFiltrados.add(comentario);
            }
        }
        return listaComentariosFiltrados;
    }

    public Integer buscarQuantidadeRespostas(@Valid BuscarQuantidadeRepostasComentarioCommand cmd) {
        return retornaComentario(cmd.getIdComentario()).getQtdRespostas();
    }

    public void deletar(@Valid DeletarComentarioCommand cmd) {
        Comentario comentario = retornaComentario(cmd.getIdComentario());
        Video video = videoRepository.getById(comentario.getIdVideo().getId());
        if (!(cmd.getIdUsuario().equals(comentario.getIdUsuario().getIdUsuario()))) {
            throw new AcaoNaoPermitidaException();
        }
        video.setQtdComentarios(video.getQtdComentarios() - 1);
        videoRepository.save(video);
        comentarioRepository.delete(comentario);
    }

    public Comentario retornaComentario(String idComentario) {
        Optional<Comentario> optional = comentarioRepository.findById(idComentario);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NaoEncontradoException("Comentário não encontrado!");
    }

}