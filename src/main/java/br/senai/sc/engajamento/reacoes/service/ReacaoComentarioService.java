package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.BuscarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.CriarReacaoComentarioCommand;
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

    public void criar(CriarReacaoComentarioCommand cmd) {
        try {
            ReacaoComentario reacao = repository.findByIdUsuarioAndIdComentario(cmd.getIdUsuario(), cmd.getIdComentario());

            if (reacao.isCurtida() == cmd.getCurtida()) {
                repository.deleteByIdUsuarioAndIdComentario(cmd.getIdUsuario(), cmd.getIdComentario());
            } else {
                reacao.setCurtida(!reacao.isCurtida());
            }
        } catch (NaoEncontradoException e) {
            ReacaoComentario reacao = new ReacaoComentario();
            BeanUtils.copyProperties(cmd, reacao);
            repository.save(reacao);
        }
    }

    public ReacaoComentario buscarUm(BuscarUmReacaoComentarioCommand cmd) {
        return repository.findByIdUsuarioAndIdComentario(cmd.getIdUsuario(), cmd.getIdComentario());
    }

    public List<ReacaoComentario> buscarTodos() {
        return repository.findAll();
    }
}
