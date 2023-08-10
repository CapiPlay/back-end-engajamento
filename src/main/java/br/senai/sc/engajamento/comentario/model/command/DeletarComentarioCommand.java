package br.senai.sc.engajamento.comentario.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeletarComentarioCommand {

    @NotNull
    private String idComentario;
    @NotNull
    private String idUsuario;

    public DeletarComentarioCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

}