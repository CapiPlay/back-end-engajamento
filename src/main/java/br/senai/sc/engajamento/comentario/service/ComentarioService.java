package br.senai.sc.engajamento.comentario.service;

import br.senai.sc.engajamento.comentario.model.command.*;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.repository.ComentarioRepository;
import br.senai.sc.engajamento.exception.AcaoNaoPermitidaException;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.repository.ReacaoComentarioRepository;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRespostaRepository;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.repository.RespostaRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import br.senai.sc.engajamento.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ComentarioService {

    private ReacaoComentarioRepository reacaoComentarioRepository;
    private ReacaoRespostaRepository reacaoRespostaRepository;
    private ComentarioRepository comentarioRepository;
    private RespostaRepository respostaRepository;
    private UsuarioRepository usuarioRepository;
    private VideoRepository videoRepository;
    private VideoService videoService;

    public Comentario criar(@Valid CriarComentarioCommand cmd) {
        Video video = videoRepository.getById(cmd.getIdVideo());

        if(!video.getEhInativado()){
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
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public Comentario buscarUm(@Valid BuscarUmComentarioCommand cmd) {
        Comentario comentario = retornaComentario(cmd.getIdComentario());
        if(!comentario.getIdVideo().getEhInativado()){
            return retornaComentario(cmd.getIdComentario());
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public Page<Comentario> buscarTodosPorVideo(
            @Valid BuscarTodosPorVideoComentarioCommand cmd, int page) {
        Video video = videoRepository.getById(cmd.getIdVideo());
        if(!video.getEhInativado()){
            Pageable pageable = PageRequest.of(page, 5);
            Page<Comentario> list = comentarioRepository.findAllByIdVideoOrderByDataHora(video, pageable);
            return list;
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public Page<Comentario> buscarTodosPorData(@Valid BuscarTodosPorDataComentarioCommand cmd, int page)  {
        List<Comentario> listaComentariosFiltrados = new ArrayList<>();
        Video video = videoRepository.getById(cmd.getIdVideo());

        if(!video.getEhInativado()){
            Pageable pageable = PageRequest.of(page, 5);
            List<Comentario> listaComentarios =
                    comentarioRepository.findAllByIdVideo(video);
            for (Comentario comentario : listaComentarios) {
                if (comentario.getDataHora().toLocalDate().equals(cmd.getData())) {
                    listaComentariosFiltrados.add(comentario);
                }
            }

            return converteListParaPage(listaComentariosFiltrados, pageable);
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public Integer buscarQuantidadeRespostas(@Valid BuscarQuantidadeRepostasComentarioCommand cmd) {
        Comentario comentario = retornaComentario(cmd.getIdComentario());
        if(!comentario.getIdVideo().getEhInativado()){
            return retornaComentario(cmd.getIdComentario()).getQtdRespostas();
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public void deletar(@Valid DeletarComentarioCommand cmd) {
        Comentario comentario = retornaComentario(cmd.getIdComentario());
        Video video = videoRepository.getById(comentario.getIdVideo().getId());
        if(!video.getEhInativado()){
            if (!(cmd.getIdUsuario().equals(comentario.getIdUsuario().getIdUsuario()))) {
                throw new AcaoNaoPermitidaException();
            }
            video.setQtdComentarios(video.getQtdComentarios() - 1);
            videoRepository.save(video);
            videoService.editarPontuacao(video);

            reacaoComentarioRepository.deleteAll(comentario.getReacaoComentarioList());
            List<Resposta> respostas = comentario.getRespostas();
            for (Resposta resposta : respostas) {
                reacaoRespostaRepository.deleteAll(resposta.getReacaoRespostaList());
            }
            respostaRepository.deleteAll(respostas);
            comentarioRepository.delete(comentario);
        } else {
            throw new NaoEncontradoException("Vídeo não encontrado");
        }
    }

    public Comentario retornaComentario(String idComentario) {
        Optional<Comentario> optional = comentarioRepository.findById(idComentario);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NaoEncontradoException("Comentário não encontrado!");
    }

    public Page<Comentario> converteListParaPage(List<Comentario> list, Pageable pageable) {
        // Obtém o índice de início da página com base no offset do Pageable
        int start = (int) pageable.getOffset();

        // Calcula o índice final da página, levando em consideração o tamanho da página
        int end = Math.min((start + pageable.getPageSize()), list.size());

        // Cria uma nova instância de PageImpl com a parte relevante da lista,
        // as informações de paginação e o tamanho total da lista original
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

}