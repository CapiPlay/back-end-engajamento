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
        BuscarUmComentarioCommand comentario = new BuscarUmComentarioCommand(cmd.getIdComentario());
        return respostaRepository.findAllByIdComentario(comentarioService.buscarUm(comentario));
    }

    public void deletar(
            DeletarRespostaCommand cmd
    ) {
        try {
            Resposta resposta = retornaResposta(cmd.getIdResposta());
            if (!(cmd.getIdUsuario().equals(resposta.getIdUsuario().getIdUsuario()))) {
                throw new NaoEncontradoException("Resposta não encontrada!");
            }
            respostaRepository.delete(resposta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Resposta retornaResposta(String idResposta) {
        return respostaRepository.findById(idResposta).orElseThrow(NaoEncontradoException::new);
    }

}