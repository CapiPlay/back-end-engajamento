package br.senai.sc.engajamento.resposta.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarRespostaCommand {
    @NotNull
    private String idUsuario;
    @NotNull
    private String idComentario;
    @NotNull
    private String texto;
}
