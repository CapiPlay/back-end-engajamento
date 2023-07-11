package br.senai.sc.engajamento.historico.service;

import br.senai.sc.engajamento.historico.model.commands.BuscarUmHistoricoCommand;
import br.senai.sc.engajamento.historico.model.commands.CriarHistoricoCommand;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.repository.HistoricoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Data
public class HistoricoService {

    private HistoricoRepository historicoRepository;

    public void criar(CriarHistoricoCommand cmd){
        Historico historico = new Historico();
        BeanUtils.copyProperties(cmd, historico);
        historicoRepository.save(historico);
    }

    public Historico buscarUm(BuscarUmHistoricoCommand cmd){
        return retornaHistorico(cmd.getIdUsuario(), cmd.getIdVideo());
    }

    public Historico retornaHistorico(UUID idUsuario, UUID idVideo){
        Historico historico = null;
        try{
            historico = historicoRepository.findByIdUsuarioAndIdVideo(idUsuario, idVideo);
        } catch (Exception e){
            System.out.printf(e.getMessage());
        }
        return historico;
    }
}
