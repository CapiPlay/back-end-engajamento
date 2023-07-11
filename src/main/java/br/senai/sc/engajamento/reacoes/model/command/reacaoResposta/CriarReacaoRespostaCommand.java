package br.senai.sc.engajamento.reacoes.model.command.reacaoResposta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CriarReacaoRespostaCommand {
    @NotNull
    private UUID idUsuario;
    @NotNull
    private UUID idResposta;
    @NotNull
    private Boolean curtida;
}
