package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
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
            ReacaoRespota reacao = repository.findByIdUsuarioAndIdResposta(cmd.getIdUsuario(), cmd.getIdResposta());

            if (reacao.isCurtida() == cmd.getCurtida()) {
                repository.deleteByIdUsuarioAndIdResposta(cmd.getIdUsuario(), cmd.getIdResposta());
            } else {
                reacao.setCurtida(!reacao.isCurtida());
            }
        } catch (NaoEncontradoException e) {
            ReacaoRespota reacao = new ReacaoRespota();
            BeanUtils.copyProperties(cmd, reacao);
            repository.save(reacao);
        }
    }

    public ReacaoRespota buscarUm(BuscarUmReacaoRespostaCommand cmd) {
        return repository.findByIdUsuarioAndIdResposta(cmd.getIdUsuario(), cmd.getIdResposta());
    }

    public List<ReacaoRespota> buscarTodos() {
        return repository.findAll();
    }
}
