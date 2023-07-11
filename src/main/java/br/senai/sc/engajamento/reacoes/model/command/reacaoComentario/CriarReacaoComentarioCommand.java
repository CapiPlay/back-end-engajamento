package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CriarReacaoComentarioCommand {
    @NotNull
    private UUID idUsuario;
    @NotNull
    private UUID idComentario;
    @NotNull
    private Boolean curtida;
}
