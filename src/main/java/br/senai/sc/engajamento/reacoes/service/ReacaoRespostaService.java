package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.AlternarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.DeletarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRespostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoRespostaService {
    private final ReacaoRespostaRepository repository;

    public void criar(CriarReacaoRespostaCommand cmd) {
        try {
            repository.findById(cmd.getIdReacaoResposta()).
                    orElseThrow(NaoEncontradoException::new);
            repository.deleteById(cmd.getIdReacaoResposta());
        } catch (NaoEncontradoException e) {
            ReacaoRespota reacao = new ReacaoRespota();
            BeanUtils.copyProperties(cmd, reacao);
            repository.save(reacao);
        }
    }

    public ReacaoRespota buscarUm(BuscarUmReacaoRespostaCommand cmd) {
        return repository.findById(cmd.getIdReacaoResposta()).orElseThrow(NaoEncontradoException::new);
    }

    public List<ReacaoRespota> buscarTodos() {
        return repository.findAll();
    }

    public void alternar(AlternarReacaoRespostaCommand cmd) {
        try {
            ReacaoRespota reacao = repository.findById(cmd.getIdReacaoResposta()).
                    orElseThrow(NaoEncontradoException::new);
            if (reacao.isCurtida() == cmd.isCurtida()) {
                repository.deleteById(cmd.getIdReacaoResposta());
            } else {
                reacao.setCurtida(!reacao.isCurtida());
            }
        } catch (NaoEncontradoException e) {
            ReacaoRespota reacao = new ReacaoRespota();
            BeanUtils.copyProperties(cmd, reacao);
            repository.save(reacao);
        }
    }

    public void deletar(DeletarUmReacaoRespostaCommand cmd) {
        repository.deleteById(cmd.getIdReacaoResposta());
    }
}
