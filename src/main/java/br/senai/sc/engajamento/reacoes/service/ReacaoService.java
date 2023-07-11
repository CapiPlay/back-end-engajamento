package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.CriarReacaoCommand;
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

    public void criar(CriarReacaoCommand cmd) {
        try {
            Reacao reacao = repository.findByIdUsuarioAndIdVideo(cmd.getIdUsuario(), cmd.getIdVideo());

            if (reacao.isCurtida() == cmd.getCurtida()) {
                repository.deleteByIdUsuarioAndIdVideo(cmd.getIdUsuario(), cmd.getIdVideo());
            } else {
                reacao.setCurtida(!reacao.isCurtida());
            }
        } catch (NaoEncontradoException e) {
            Reacao reacao = new Reacao();
            BeanUtils.copyProperties(cmd, reacao);
            repository.save(reacao);
        }
    }

    public Reacao buscarUm(BuscarUmReacaoCommand cmd) {
        return repository.findByIdUsuarioAndIdVideo(cmd.getIdUsuario(), cmd.getIdVideo());
    }

    public List<Reacao> buscarTodos() {
        return repository.findAll();
    }
}
