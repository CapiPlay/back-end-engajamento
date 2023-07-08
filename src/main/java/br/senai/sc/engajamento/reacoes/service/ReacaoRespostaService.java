package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.DeletarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.EditarReacaoRespostaCommand;
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

    public ReacaoRespota criar(CriarReacaoRespostaCommand cmd) {
        ReacaoRespota reacaoRespota = new ReacaoRespota();
        BeanUtils.copyProperties(cmd, reacaoRespota);
        return repository.save(reacaoRespota);
    }

    public ReacaoRespota buscarUm(BuscarUmReacaoRespostaCommand cmd) {
        return repository.findById(cmd.getIdReacaoResposta()).orElseThrow(NaoEncontradoException::new);
    }

    public List<ReacaoRespota> buscarTodos() {
        return repository.findAll();
    }

    public ReacaoRespota editar(EditarReacaoRespostaCommand cmd) {
        ReacaoRespota reacaoRespota = new ReacaoRespota();
        BeanUtils.copyProperties(cmd, reacaoRespota);
        return repository.save(reacaoRespota);
    }

    public void deletar(DeletarUmReacaoRespostaCommand cmd) {
        repository.deleteById(cmd.getIdReacaoResposta());
    }
}
