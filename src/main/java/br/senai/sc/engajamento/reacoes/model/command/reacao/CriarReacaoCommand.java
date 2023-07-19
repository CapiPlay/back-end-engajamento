package br.senai.sc.engajamento.reacoes.model.command.reacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarReacaoCommand {
    @NotNull
    private String idUsuario;
    @NotNull
    private String idVideo;
    @NotNull
    private Boolean curtida;
}
