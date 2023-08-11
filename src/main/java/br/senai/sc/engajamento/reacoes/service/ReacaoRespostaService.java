package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarTodosPorComentarioReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoResposta;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRespostaRepository;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.repository.RespostaRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoRespostaService {
    private final ReacaoRespostaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final RespostaRepository respostaRepository;

    @Transactional
    public void criar(@Valid CriarReacaoRespostaCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Resposta resposta = respostaRepository.getById(cmd.getIdResposta());

        ReacaoResposta reacaoExistente = repository.findByIdUsuarioAndIdResposta(usuario, resposta);
        if (reacaoExistente == null) {
            ReacaoResposta reacao = new ReacaoResposta();
            reacao.setIdResposta(resposta);
            reacao.setIdUsuario(usuario);
            reacao.setCurtida(cmd.getCurtida());
            repository.save(reacao);
        } else if (reacaoExistente.isCurtida() == cmd.getCurtida()) {
            repository.deleteByIdUsuarioAndIdResposta(usuario, resposta);
        } else {
            reacaoExistente.setCurtida(!reacaoExistente.isCurtida());
            repository.save(reacaoExistente);
        }
    }

    public ReacaoResposta buscarUm(@Valid BuscarUmReacaoRespostaCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Resposta resposta = respostaRepository.getById(cmd.getIdResposta());

        ReacaoResposta reacao = repository.findByIdUsuarioAndIdResposta(usuario, resposta);
        if (reacao == null) {
            throw new NaoEncontradoException("Reação da resposta não encontrada!");
        }

        return reacao;
    }

    public List<ReacaoResposta> buscarTodos(@Valid BuscarTodosPorComentarioReacaoRespostaCommand cmd) {
        Resposta resposta = respostaRepository.getById(cmd.getIdResposta());

        return repository.findAllByIdResposta(resposta);
    }
}
