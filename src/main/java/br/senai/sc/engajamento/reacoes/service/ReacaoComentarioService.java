package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.BuscarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.CriarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.DeletarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.EditarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.repository.ReacaoComentarioRepository;
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
        return repository.findById(cmd.getIdReacaoComentario()).orElseThrow(NaoEncontradoException::new);
    }

    public List<ReacaoComentario> buscarTodos() {
        return repository.findAll();
    }

    public ReacaoComentario editar(EditarReacaoComentarioCommand cmd) {
        ReacaoComentario reacaoComentario = new ReacaoComentario();
        BeanUtils.copyProperties(cmd, reacaoComentario);
        return repository.save(reacaoComentario);
    }

    public void deletar(DeletarUmReacaoComentarioCommand cmd) {
        repository.deleteById(cmd.getIdReacaoComentario());
    }
}
