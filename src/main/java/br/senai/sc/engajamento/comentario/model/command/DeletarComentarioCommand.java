package br.senai.sc.engajamento.comentario.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeletarComentarioCommand {

    @NotNull(message = "ID do comentário não informado")
    private String idComentario;

    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;


    public DeletarComentarioCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}