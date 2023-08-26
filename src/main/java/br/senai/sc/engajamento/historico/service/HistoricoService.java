package br.senai.sc.engajamento.historico.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.historico.model.commands.*;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.repository.HistoricoRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import br.senai.sc.engajamento.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final VideoRepository videoRepository;
    private final VideoService videoService;

    public void criar(@Valid CriarHistoricoCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Video video = videoRepository.getById(cmd.getIdVideo());

        if (!video.getEhInativado()) {
            if (historicoRepository.findByIdUsuarioAndIdVideo(usuario, video) == null) {
                Historico historico = new Historico(usuario, video, cmd.getPercentagemSomada());
                historicoRepository.save(historico);
            } else {
                Historico historico = retornaHistorico(cmd.getIdUsuario(), cmd.getIdVideo());
                historico.setQtdVisualizadas(historico.getQtdVisualizadas() + 1);
                historico.setPercentagemSomada(historico.getPercentagemSomada() + cmd.getPercentagemSomada());
                historico.setDataHora(ZonedDateTime.now());
                historicoRepository.save(historico);
            }

            videoService.editarPontuacao(video);
        } else {
            throw new NaoEncontradoException("Vídeo não encontrado ");
        }
    }

    public Historico buscarUm(@Valid BuscarUmHistoricoCommand cmd) {
        Video video = videoRepository.getById(cmd.getIdVideo());

        if (!video.getEhInativado()) {
            return retornaHistorico(cmd.getIdUsuario(), cmd.getIdVideo());
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public List<Historico> buscarTodosPorData(@Valid BuscarTodosPorDataHistoricoCommand cmd) {
        List<Historico> listaHistoricosFiltrados = new ArrayList<>();

        List<Historico> listaHistoricos =
                historicoRepository.findAllByIdUsuario(usuarioRepository.findById(cmd.getIdUsuario())
                        .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!")));
        for (Historico historico: listaHistoricos) {
            Video video = videoRepository.getById(historico.getIdVideo().getId());
            if(historico.getDataHora().toLocalDate().equals(cmd.getData()) && !(video.getEhInativado())) {
                listaHistoricosFiltrados.add(historico);
            }
        }
        return listaHistoricosFiltrados;
    }

    public List<Historico> buscarTodosPorUsuario(String idUsuario) {
        List<Historico> list = historicoRepository.findAllByIdUsuario(usuarioRepository.findById(idUsuario)
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!")));
        List<Historico> validList = new ArrayList<>();

        for(Historico historico: list){
            if(!historico.getIdVideo().getEhInativado()){
                validList.add(historico);
            }
        }
        return validList;
    }

    public List<Historico> buscarTodosPorVideo(@Valid Video video){
        if(!video.getEhInativado()){
            return historicoRepository.findAllByIdVideo(video);
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public Historico retornaHistorico(String idUsuario, String idVideo) {
        Usuario usuario = usuarioRepository.getById(idUsuario);
        Video video = videoRepository.getById(idVideo);

        if(!video.getEhInativado()){
            Historico historico = historicoRepository.findByIdUsuarioAndIdVideo(usuario, video);
            if(historico == null){
                throw new NaoEncontradoException("Histórico não encontrado!");
            }

            return historico;
        }

        throw new NaoEncontradoException("Vídeo não encontrado");
    }
}

