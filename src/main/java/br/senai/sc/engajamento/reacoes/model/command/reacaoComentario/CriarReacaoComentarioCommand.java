package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarReacaoComentarioCommand {
    @NotNull
    private String idUsuario;
    @NotNull
    private String idComentario;
    @NotNull
    private Boolean curtida;
}
