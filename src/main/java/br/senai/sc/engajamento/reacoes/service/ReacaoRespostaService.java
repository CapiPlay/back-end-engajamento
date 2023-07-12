package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRespostaRepository;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.service.RespostaService;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoRespostaService {
    private final ReacaoRespostaRepository repository;
    private final UsuarioService usuarioService;
    private final RespostaService respostaService;

    public void criar(CriarReacaoRespostaCommand cmd) {
        try {
            Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
            Resposta resposta = respostaService.retornaResposta(cmd.getIdResposta());
            ReacaoRespota reacao = repository.findByIdUsuarioAndIdResposta(usuario, resposta);

            if (reacao.isCurtida() == cmd.getCurtida()) {
                repository.deleteByIdUsuarioAndIdResposta(usuario, resposta);
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
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        Resposta resposta = respostaService.retornaResposta(cmd.getIdResposta());
        return repository.findByIdUsuarioAndIdResposta(usuario, resposta);
    }

    public List<ReacaoRespota> buscarTodos() {
        return repository.findAll();
    }
}
