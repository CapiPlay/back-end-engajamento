package br.senai.sc.engajamento.comentario.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditarComentarioCommand {
    @NotNull(message = "ID do comentário não informado")
    private String idComentario;

    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "Texto do comentário não informado")
    private String texto;
}