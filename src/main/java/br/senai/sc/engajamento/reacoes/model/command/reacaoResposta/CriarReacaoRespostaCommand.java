package br.senai.sc.engajamento.reacoes.model.command.reacaoResposta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarReacaoRespostaCommand {
    @NotNull
    private String idUsuario;
    @NotNull
    private String idResposta;
    @NotNull
    private Boolean curtida;
}
