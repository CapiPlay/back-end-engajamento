package br.senai.sc.engajamento.reacoes.model.command.reacao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class EditarReacaoCommand {
    private UUID idReacao;
    private boolean curtida;
}
