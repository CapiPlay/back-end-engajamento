package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarTodosPorComentarioReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRespostaRepository;
import br.senai.sc.engajamento.resposta.controller.RespostaController;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.repository.RespostaRepository;
import br.senai.sc.engajamento.resposta.service.RespostaService;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
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

    public void criar(@Valid CriarReacaoRespostaCommand cmd) {

        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Resposta resposta = respostaRepository.getById(cmd.getIdResposta());

        ReacaoRespota reacaoExistente = repository.findByIdUsuarioAndIdResposta(usuario, resposta);
        if (reacaoExistente == null) {
            ReacaoRespota reacao = new ReacaoRespota();
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

    public ReacaoRespota buscarUm(@Valid BuscarUmReacaoRespostaCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Resposta resposta = respostaRepository.getById(cmd.getIdResposta());

        ReacaoRespota reacao = repository.findByIdUsuarioAndIdResposta(usuario, resposta);
        if (reacao == null) {
            throw new NaoEncontradoException("Reação da resposta não encontrada!");
        }

        return reacao;
    }

    public List<ReacaoRespota> buscarTodos(@Valid BuscarTodosPorComentarioReacaoRespostaCommand cmd) {
        Resposta resposta = respostaRepository.getById(cmd.getIdResposta());

        return repository.findAllByIdResposta(resposta);
    }
}
