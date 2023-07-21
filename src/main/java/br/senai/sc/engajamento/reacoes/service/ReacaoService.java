package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarTodosPorVideoReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.CriarReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.model.exception.NaoEncontradoReacaoException;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoService {
    private final ReacaoRepository repository;
    private final UsuarioService usuarioService;
    private final VideoService videoService;

    public void criar(CriarReacaoCommand cmd) {
        try {
            Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
            Video video = videoService.retornaVideo(cmd.getIdVideo());
            Reacao reacao = repository.findByIdUsuarioAndIdVideo(usuario, video);
            if (reacao == null) {
                throw new NaoEncontradoReacaoException();
            } else if (reacao.isCurtida() == cmd.getCurtida()) {
                repository.deleteByIdUsuarioAndIdVideo(usuario, video);
            } else {
                reacao.setCurtida(!reacao.isCurtida());
                repository.save(reacao);
            }
        } catch (NaoEncontradoException e) {
            Reacao reacao = new Reacao();
            reacao.setIdUsuario(usuarioService.retornaUsuario(cmd.getIdUsuario()));
            reacao.setIdVideo(videoService.retornaVideo(cmd.getIdVideo()));
            reacao.setCurtida(cmd.getCurtida());

            repository.save(reacao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Reacao buscarUm(BuscarUmReacaoCommand cmd) {
        Reacao reacao = null;
        try {
            Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
            Video video = videoService.retornaVideo(cmd.getIdVideo());
            reacao = repository.findByIdUsuarioAndIdVideo(usuario, video);
            if (reacao == null) {
                throw new NaoEncontradoReacaoException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return reacao;
    }

    public List<Reacao> buscarTodosPorVideo(BuscarTodosPorVideoReacaoCommand cmd) {
        return repository.findAllByIdVideo(videoService.retornaVideo(cmd.getIdVideo()));
    }
}
