package br.senai.sc.engajamento.reacaoResposta.service;

import br.senai.sc.engajamento.reacaoResposta.model.command.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacaoResposta.model.command.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacaoResposta.model.command.DeletarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacaoResposta.model.command.EditarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacaoResposta.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacaoResposta.repository.ReacaoRespostaRepository;
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
//        TODO fazer exception
        return repository.findById(cmd.getIdReacaoResposta()).orElseThrow();
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
