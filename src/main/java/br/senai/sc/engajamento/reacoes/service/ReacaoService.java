package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacao.AlternarReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.DeletarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoService {
    private final ReacaoRepository repository;

    public void criar(AlternarReacaoCommand cmd) {
        try {
            repository.findById(cmd.getIdReacao()).
                    orElseThrow(NaoEncontradoException::new);
            repository.deleteById(cmd.getIdReacao());
        } catch (NaoEncontradoException e) {
            Reacao reacao = new Reacao();
            BeanUtils.copyProperties(cmd, reacao);
            repository.save(reacao);
        }
    }

    public Reacao buscarUm(BuscarUmReacaoCommand cmd) {
        return repository.findById(cmd.getIdReacao()).
                orElseThrow(NaoEncontradoException::new);
    }

    public List<Reacao> buscarTodos() {
        return repository.findAll();
    }

    public void alternar(AlternarReacaoCommand cmd) {
        try {
            Reacao reacao = repository.findById(cmd.getIdReacao()).
                    orElseThrow(NaoEncontradoException::new);
            if (reacao.isCurtida() == cmd.isCurtida()) {
                repository.deleteById(cmd.getIdReacao());
            } else {
                reacao.setCurtida(!reacao.isCurtida());
            }
        } catch (NaoEncontradoException e) {
            Reacao reacao = new Reacao();
            BeanUtils.copyProperties(cmd, reacao);
            repository.save(reacao);
        }
    }

    public void deletar(DeletarUmReacaoCommand cmd) {
        repository.deleteById(cmd.getIdReacao());
    }
}
