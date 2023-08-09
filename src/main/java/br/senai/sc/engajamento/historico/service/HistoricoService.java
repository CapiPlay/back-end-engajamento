package br.senai.sc.engajamento.historico.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.historico.model.commands.*;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.repository.HistoricoRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final UsuarioService usuarioService;
    private final VideoService videoService;

    public void criar(CriarHistoricoCommand cmd) {
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        Video video = videoService.retornaVideo(cmd.getIdVideo());
        if(historicoRepository.findByIdUsuarioAndIdVideo(usuario, video) != null){
            Historico historico = new Historico(usuario, video, cmd.getPercentagemSomada());
            historicoRepository.save(historico);
        } else {
            Historico historico = retornaHistorico(cmd.getIdUsuario(), cmd.getIdVideo());
            historico.setQtdVisualizadas(historico.getQtdVisualizadas() + 1);
            historico.setPercentagemSomada(historico.getPercentagemSomada() + cmd.getPercentagemSomada());
            historico.setDataHora(ZonedDateTime.now());
        }
    }

    public Historico buscarUm(BuscarUmHistoricoCommand cmd) {
        return retornaHistorico(cmd.getIdUsuario(), cmd.getIdVideo());
    }

    public List<Historico> buscarTodosPorData(BuscarTodosPorDataHistoricoCommand cmd){
        List<Historico> listaHistoricosFiltrados = new ArrayList<>();

        List<Historico> listaHistoricos =
                historicoRepository.findAllByIdUsuario(usuarioService.retornaUsuario(cmd.getIdUsuario()));
        for (Historico historico: listaHistoricos) {
            if(historico.getDataHora().toLocalDate().equals(cmd.getData())) {
                listaHistoricosFiltrados.add(historico);
            }
        }
        return listaHistoricosFiltrados;
    }

    public List<Historico> buscarTodosPorUsuario(BuscarTodosPorUsuarioHistoricoCommand cmd){
        return historicoRepository.findAllByIdUsuario(usuarioService.retornaUsuario(cmd.getIdUsuario()));
    }

    public List<Historico> buscarTodosPorVideo(Video video){
        return historicoRepository.findAllByIdVideo(video);
    }

    public Historico retornaHistorico(String idUsuario, String idVideo) {
        Historico historico = null;
        try {
            Usuario usuario = usuarioService.retornaUsuario(idUsuario);
            Video video = videoService.retornaVideo(idVideo);

            historico = historicoRepository.findByIdUsuarioAndIdVideo(usuario, video);
            if(historico == null){
                throw new NaoEncontradoException("Histórico não encontrado!");
            }
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return historico;
    }
}
