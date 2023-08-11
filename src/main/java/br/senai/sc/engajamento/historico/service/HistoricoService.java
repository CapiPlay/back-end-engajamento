package br.senai.sc.engajamento.historico.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.historico.model.commands.*;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.repository.HistoricoRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    public void criar(@Valid CriarHistoricoCommand cmd) {
        Usuario usuario = usuarioRepository.findById(cmd.getIdUsuario()).orElseThrow(()->new NaoEncontradoException("Usuário não encontrado!"));
        Video video = videoRepository.findById(cmd.getIdVideo()).orElseThrow(()->new NaoEncontradoException("Vídeo não encontrado!"));

        if(historicoRepository.findByIdUsuarioAndIdVideo(usuario, video) != null){
            Historico historico = new Historico(usuario, video, cmd.getPercentagemSomada());
            historicoRepository.save(historico);
        } else {
            Historico historico = retornaHistorico(cmd.getIdUsuario(), cmd.getIdVideo());
            historico.setQtdVisualizadas(historico.getQtdVisualizadas() + 1);
            historico.setPercentagemSomada(historico.getPercentagemSomada() + cmd.getPercentagemSomada());
            historico.setDataHora(ZonedDateTime.now());
            historicoRepository.save(historico);
        }
    }

    public Historico buscarUm(@Valid BuscarUmHistoricoCommand cmd) {
        return retornaHistorico(cmd.getIdUsuario(), cmd.getIdVideo());
    }

    public List<Historico> buscarTodosPorData(@Valid BuscarTodosPorDataHistoricoCommand cmd) {
        List<Historico> listaHistoricosFiltrados = new ArrayList<>();

        List<Historico> listaHistoricos =
                historicoRepository.findAllByIdUsuario(usuarioRepository.findById(cmd.getIdUsuario())
                        .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!")));
        for (Historico historico: listaHistoricos) {
            if(historico.getDataHora().toLocalDate().equals(cmd.getData())) {
                listaHistoricosFiltrados.add(historico);
            }
        }
        return listaHistoricosFiltrados;
    }

    public List<Historico> buscarTodosPorUsuario(String idUsuario) {
        return historicoRepository.findAllByIdUsuario(usuarioRepository.findById(idUsuario)
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!")));
    }

    public List<Historico> buscarTodosPorVideo(@Valid Video video){
        return historicoRepository.findAllByIdVideo(video);
    }

    public Historico retornaHistorico(String idUsuario, String idVideo) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!"));

        Video video = videoRepository.findById(idVideo)
                .orElseThrow(()-> new NaoEncontradoException("Vídeo não encontrado!"));

        Historico historico = historicoRepository.findByIdUsuarioAndIdVideo(usuario, video);

        if(historico == null){
            throw new NaoEncontradoException("Histórico não encontrado!");
        }

        return historico;
    }
}

