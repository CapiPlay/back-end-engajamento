package br.senai.sc.engajamento.reacaoComentario.service;

import br.senai.sc.engajamento.reacao.model.command.DeletarUmReacaoCommand;
import br.senai.sc.engajamento.reacaoComentario.model.command.BuscarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacaoComentario.model.command.CriarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacaoComentario.model.command.EditarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacaoComentario.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacaoComentario.repository.ReacaoComentarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoComentarioService {
    private final ReacaoComentarioRepository repository;

    public ReacaoComentario criar(CriarReacaoComentarioCommand cmd) {
        ReacaoComentario reacaoComentario = new ReacaoComentario();
        BeanUtils.copyProperties(cmd, reacaoComentario);
        return reacaoComentario;
    }

    public ReacaoComentario buscarUm(BuscarUmReacaoComentarioCommand cmd) {
//        TODO fazer exception
        return repository.findById(cmd.getIdReacaoComentario()).orElseThrow();
    }

    public List<ReacaoComentario> buscarTodos() {
        return repository.findAll();
    }

    public ReacaoComentario editar(EditarReacaoComentarioCommand cmd) {
        ReacaoComentario reacaoComentario = new ReacaoComentario();
        BeanUtils.copyProperties(cmd, reacaoComentario);
        return repository.save(reacaoComentario);
    }

    public void deletar(DeletarUmReacaoCommand cmd) {
        repository.deleteById(cmd.getIdReacao());
    }
}
