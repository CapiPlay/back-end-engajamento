package br.senai.sc.engajamento.reacao.service;

import br.senai.sc.engajamento.reacao.model.command.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacao.model.command.CriarReacaoCommand;
import br.senai.sc.engajamento.reacao.model.entity.Reacao;
import br.senai.sc.engajamento.reacao.repository.ReacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReacaoService {
    private ReacaoRepository repository;

    private Reacao criar(CriarReacaoCommand cmd) {
        Reacao reacao = new Reacao();
        BeanUtils.copyProperties(cmd, reacao);
        return repository.save(reacao);
    }

    private Reacao buscarUm(BuscarUmReacaoCommand cmd) {
//        TODO fazer
        return repository.findById(cmd.getIdReacao()).orElseThrow();
    }
}
