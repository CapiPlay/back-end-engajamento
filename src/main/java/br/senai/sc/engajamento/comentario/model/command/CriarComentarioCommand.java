package br.senai.sc.engajamento.comentario.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class CriarComentarioCommand {

    private String idUsuario;
    private String idVideo;
    private String texto;

    public CriarComentarioCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

}