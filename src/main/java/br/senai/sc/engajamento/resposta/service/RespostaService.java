package br.senai.sc.engajamento.resposta.service;

import br.senai.sc.engajamento.comentario.model.command.BuscarUmComentarioCommand;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.service.ComentarioService;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.resposta.model.command.BuscarTodosPorComentarioRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.BuscarUmaRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.CriarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.DeletarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.repository.RespostaRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Data
public class RespostaService {

    private RespostaRepository respostaRepository;
    private UsuarioService usuarioService;
    private ComentarioService comentarioService;

    public Resposta criar(
            CriarRespostaCommand cmd
    ) {
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        Comentario comentario = comentarioService.retornaComentario(cmd.getIdComentario());
        Resposta resposta = new Resposta(
                cmd.getTexto(),
                usuario,
                comentario
        );
        return respostaRepository.save(resposta);
    }

    public Resposta buscarUm(
            BuscarUmaRespostaCommand cmd
    ) {
        return retornaResposta(cmd.getIdResposta());
    }

    public List<Resposta> buscarTodosPorComentario(
            BuscarTodosPorComentarioRespostaCommand cmd
    ) {
        BuscarUmComentarioCommand cmd2 = new BuscarUmComentarioCommand(cmd.getIdComentario());
        return respostaRepository.findAllByComentario(comentarioService.buscarUm(cmd2));
    }

    public void deletar(
            DeletarRespostaCommand cmd
    ) {
        Resposta resposta = retornaResposta(cmd.getIdResposta());
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        if (usuario.getIdUsuario() != cmd.getIdResposta()) {
            throw new NaoEncontradoException();
        } else {
            respostaRepository.delete(resposta);
        }
    }

    public Resposta retornaResposta(UUID idResposta) {
        Optional<Resposta> optional = respostaRepository.findById(idResposta);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NaoEncontradoException();
    }

}