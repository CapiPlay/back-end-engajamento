package br.senai.sc.engajamento.comentario.model.command;

import lombok.Data;

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