package br.senai.sc.engajamento.reacao.service;

import br.senai.sc.engajamento.reacao.model.command.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacao.model.command.CriarReacaoCommand;
import br.senai.sc.engajamento.reacao.model.command.DeletarUmReacaoCommand;
import br.senai.sc.engajamento.reacao.model.command.EditarReacaoCommand;
import br.senai.sc.engajamento.reacao.model.entity.Reacao;
import br.senai.sc.engajamento.reacao.repository.ReacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoService {
    private final ReacaoRepository repository;

    public void criar(CriarReacaoCommand cmd) {
        try {
            repository.findById(cmd.getIdReacao()).orElseThrow();
            repository.deleteById(cmd.getIdReacao());
        } catch (Exception e) {
            Reacao reacao = new Reacao();
            BeanUtils.copyProperties(cmd, reacao);
            repository.save(reacao);
        }
    }

    public Reacao buscarUm(BuscarUmReacaoCommand cmd) {
//        TODO fazer exception
        return repository.findById(cmd.getIdReacao()).orElseThrow();
    }

    public List<Reacao> buscarTodos() {
        return repository.findAll();
    }

    public Reacao editar(EditarReacaoCommand cmd) {
        Reacao reacao = new Reacao();
        BeanUtils.copyProperties(cmd, reacao);
        return repository.save(reacao);
    }

    public void deletar(DeletarUmReacaoCommand cmd) {
        repository.deleteById(cmd.getIdReacao());
    }
}
