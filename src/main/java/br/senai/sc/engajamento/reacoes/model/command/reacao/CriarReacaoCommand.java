package br.senai.sc.engajamento.reacoes.model.command.reacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CriarReacaoCommand {
    @NotNull
    private UUID idUsuario;
    @NotNull
    private UUID idVideo;
    @NotNull
    private Boolean curtida;
}
