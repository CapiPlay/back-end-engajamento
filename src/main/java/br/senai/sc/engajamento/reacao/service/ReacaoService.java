package br.senai.sc.engajamento.reacao.service;

import br.senai.sc.engajamento.reacao.model.entity.Reacao;
import br.senai.sc.engajamento.reacao.repository.ReacaoRepository;
import br.senai.sc.engajamento.reacao.model.command.CriarReacaoCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReacaoService {
    private ReacaoRepository repository;

    private Reacao criar(CriarReacaoCommand) {
        return
    }
}
