package br.senai.sc.engajamento.comentario.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.UUID;

@Data
@AllArgsConstructor
public class EditarComentarioCommand {

    private UUID idComentario;
    private UUID idUsuario;
    private String texto;

}
