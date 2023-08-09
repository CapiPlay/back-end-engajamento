package br.senai.sc.engajamento.comentario.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdicionarRespostaComentarioCommand {
    @NotNull(message = "")
    private String idComentario;

}
