package br.senai.sc.engajamento.comentario.model.command;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarComentarioCommand {

    private UUID idComentario;
    private UUID idUsuario;

}