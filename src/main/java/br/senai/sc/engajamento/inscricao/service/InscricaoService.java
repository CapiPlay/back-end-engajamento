package br.senai.sc.engajamento.inscricao.service;

import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.repository.InscricaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InscricaoService {
    private final InscricaoRepository repository;

    public void criar(CriarInscricaoCommand cmd) {
//        TODO fazer exception
        try {
            repository.findById(cmd.getIdReacao()).orElseThrow();
            repository.deleteById(cmd.getIdReacao());
        } catch (Exception e) {
            Inscricao inscricao = new Inscricao();
            BeanUtils.copyProperties(cmd, inscricao);
            repository.save(inscricao);
        }
    }

    public Inscricao buscarUm(BuscarUmInscricaoCommand cmd) {
//        TODO fazer exception
        return repository.findById(cmd.getIdReacao()).orElseThrow();
    }

    public List<Inscricao> buscarTodos() {
        return repository.findAll();
    }
}
