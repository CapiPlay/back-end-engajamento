package br.senai.sc.engajamento.comentario.service;

import br.senai.sc.engajamento.comentario.model.command.*;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.repository.ComentarioRepository;
import br.senai.sc.engajamento.exception.AcaoNaoPermitidaException;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import br.senai.sc.engajamento.video.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service
@AllArgsConstructor
@Data
public class ComentarioService {

    private ComentarioRepository comentarioRepository;
    private UsuarioService usuarioService;
    private VideoRepository videoRepository;
    private VideoService videoService;

    public Comentario criar(CriarComentarioCommand cmd) {
        Video video = videoRepository.findById(cmd.getIdVideo()).orElseThrow(()->new NaoEncontradoException("video nao encontrado"));
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        Comentario comentario = new Comentario(
                cmd.getTexto(),
                usuario,
                video
        );
        video.setQtdComentarios(video.getQtdComentarios() + 1);
        videoService.editarPontuacao(video);
        return comentarioRepository.save(comentario);
    }

    public Comentario buscarUm(BuscarUmComentarioCommand cmd) {
        return retornaComentario(cmd.getIdComentario());
    }

    public List<Comentario> buscarTodosPorVideo(BuscarTodosPorVideoComentarioCommand cmd) {
        Video video = videoRepository.findById(cmd.getIdVideo()).orElseThrow(()->new NaoEncontradoException("video nao encontrado"));
        List<Comentario> list = comentarioRepository.findAllByIdVideo(video);
        list.sort(Comparator.comparing(Comentario::getDataHora).reversed());
        return list;
    }

    public List<Comentario> buscarTodosPorData(BuscarTodosPorDataComentarioCommand cmd) {
        List<Comentario> listaComentariosFiltrados = new ArrayList<>();
        Video video = videoRepository.findById(cmd.getIdVideo()).orElseThrow(()->new NaoEncontradoException("video nao encontrado"));
        List<Comentario> listaComentarios =
                comentarioRepository.findAllByIdVideo(video);
        for (Comentario comentario : listaComentarios) {
            if (comentario.getDataHora().toLocalDate().equals(cmd.getData())) {
                listaComentariosFiltrados.add(comentario);
            }
        }
        return listaComentariosFiltrados;
    }

    public Integer buscarQuantidadeRespostas(BuscarQuantidadeRepostasComentarioCommand cmd) {
        Comentario comentario = retornaComentario(cmd.getIdComentario());
        return comentario.getQtdRespostas();
    }

    public void adicionarResposta(AdicionarRespostaComentarioCommand cmd) {
        Comentario comentario = retornaComentario(cmd.getIdComentario());
        comentario.setQtdRespostas(
                comentario.getQtdRespostas() + 1
        );
        comentarioRepository.save(comentario);
    }

    public void deletar(DeletarComentarioCommand cmd) {
        try {
            Comentario comentario = retornaComentario(cmd.getIdComentario());
            Video video = videoRepository.findById(comentario.getIdVideo().getId()).orElseThrow(()->new NaoEncontradoException("video nao encontrado"));
            if (!(cmd.getIdUsuario().equals(comentario.getIdUsuario().getIdUsuario()))) {
                throw new AcaoNaoPermitidaException();
            }
            video.setQtdComentarios(video.getQtdComentarios() - 1);
            comentarioRepository.delete(comentario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Comentario retornaComentario(String idComentario) {
        Optional<Comentario> comentario = comentarioRepository.findByIdComentario(idComentario);
        if (comentario.isPresent()) {
            return comentario.get();
        }
        throw new NaoEncontradoException("Comentário não encontrado!");
    }

}