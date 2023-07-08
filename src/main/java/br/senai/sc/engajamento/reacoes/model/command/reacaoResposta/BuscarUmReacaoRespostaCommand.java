package br.senai.sc.engajamento.reacoes.model.command.reacaoResposta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BuscarUmReacaoRespostaCommand {
    private UUID idReacaoResposta;
}
