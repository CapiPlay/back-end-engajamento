package br.senai.sc.engajamento.comentario.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriarComentarioCommand {

    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do vídeo não informado")
    private String idVideo;

    @NotNull(message = "Texto do comentário não informado")
    private String texto;

    public CriarComentarioCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

}
