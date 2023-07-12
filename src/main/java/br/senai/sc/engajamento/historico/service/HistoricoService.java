package br.senai.sc.engajamento.historico.service;

import br.senai.sc.engajamento.historico.model.commands.BuscarUmHistoricoCommand;
import br.senai.sc.engajamento.historico.model.commands.CriarHistoricoCommand;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.repository.HistoricoRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Data
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final UsuarioService usuarioService;
    private final VideoService videoService;

    public void criar(CriarHistoricoCommand cmd) {
        Historico historico = new Historico();
        BeanUtils.copyProperties(cmd, historico);
        historicoRepository.save(historico);
    }

    public Historico buscarUm(BuscarUmHistoricoCommand cmd) {
        return retornaHistorico(cmd.getIdUsuario(), cmd.getIdVideo());
    }

    public Historico retornaHistorico(UUID idUsuario, UUID idVideo) {
        Historico historico = null;
        try {
            Usuario usuario = usuarioService.retornaUsuario(idUsuario);
            Video video = videoService.retornaVideo(idVideo);
            historico = historicoRepository.findByIdUsuarioAndIdVideo(usuario, video);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return historico;
    }
}
