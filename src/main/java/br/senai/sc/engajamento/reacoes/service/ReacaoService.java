package br.senai.sc.engajamento.reacoes.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarTodosPorVideoReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.CriarReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.repository.ReacaoRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import br.senai.sc.engajamento.video.service.VideoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoService {
    private final ReacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final VideoRepository videoRepository;
    private final VideoService videoService;

    @Transactional
    public void criar(@Valid CriarReacaoCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Video video = videoRepository.getById(cmd.getIdVideo());
        Reacao reacaoExistente = repository.findByIdUsuarioAndIdVideo(usuario, video);

        if (!video.getEhInativado()) {
            if (reacaoExistente == null) {
                Reacao reacao = new Reacao();
                reacao.setIdUsuario(usuario);
                reacao.setIdVideo(video);
                reacao.setCurtida(cmd.getCurtida());

                if (cmd.getCurtida()) {
                    video.setQtdCurtidas(video.getQtdCurtidas() + 1);
                } else {
                    video.setQtdDescurtidas(video.getQtdDescurtidas() + 1);
                }
                videoService.editarPontuacao(video);

                repository.save(reacao);
            } else if (reacaoExistente.isCurtida() == cmd.getCurtida()) {
                if (cmd.getCurtida()) {
                    video.setQtdCurtidas(video.getQtdCurtidas() - 1);
                } else {
                    video.setQtdDescurtidas(video.getQtdDescurtidas() - 1);
                }
                videoService.editarPontuacao(video);

                repository.deleteByIdUsuarioAndIdVideo(usuario, video);
            } else {
                reacaoExistente.setCurtida(!reacaoExistente.isCurtida());
                if (cmd.getCurtida()) {
                    video.setQtdCurtidas(video.getQtdCurtidas() + 1);
                    video.setQtdDescurtidas(video.getQtdDescurtidas() - 1);
                } else {
                    video.setQtdCurtidas(video.getQtdCurtidas() - 1);
                    video.setQtdDescurtidas(video.getQtdDescurtidas() + 1);
                }
                repository.save(reacaoExistente);
            }
        } else {
            throw new NaoEncontradoException("Vídeo não encontrado");
        }
    }

    public Reacao buscarUm(@Valid BuscarUmReacaoCommand cmd) {
        Usuario usuario = usuarioRepository.getById(cmd.getIdUsuario());
        Video video = videoRepository.getById(cmd.getIdVideo());

        if (!video.getEhInativado()) {
            Reacao reacao = repository.findByIdUsuarioAndIdVideo(usuario, video);
            if (reacao == null) {
                throw new NaoEncontradoException("Reação não encontrada!");
            }
            return reacao;
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }

    public List<Reacao> buscarTodosPorVideo(@Valid BuscarTodosPorVideoReacaoCommand cmd) {
        Video video = videoRepository.getById(cmd.getIdVideo());
        if (!video.getEhInativado()) {
            return repository.findAllByIdVideo(video);
        }
        throw new NaoEncontradoException("Vídeo não encontrado");
    }
}
