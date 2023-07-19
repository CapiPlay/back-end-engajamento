package br.senai.sc.engajamento.comentario.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CriarComentarioCommand {

    private String idUsuario;
    private String idVideo;
    private String texto;

}