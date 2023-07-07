package br.senai.sc.engajamento.inscricao.service;

import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.DeletarUmInscricaoCommand;
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

    public Inscricao criar(CriarInscricaoCommand cmd) {
        Inscricao inscricao = new Inscricao();
        BeanUtils.copyProperties(cmd, inscricao);
        return repository.save(inscricao);
    }

    public Inscricao buscarUm(BuscarUmInscricaoCommand cmd) {
//        TODO fazer exception
        return repository.findById(cmd.getId()).orElseThrow();
    }

    public List<Inscricao> buscarTodos() {
        return repository.findAll();
    }

    public void deletar(DeletarUmInscricaoCommand cmd) {
        repository.deleteById(cmd.getId());
    }
}
