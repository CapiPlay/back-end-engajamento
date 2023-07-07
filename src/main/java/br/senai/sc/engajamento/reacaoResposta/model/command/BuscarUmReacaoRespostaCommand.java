package br.senai.sc.engajamento.reacaoResposta.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BuscarUmReacaoRespostaCommand {
    private UUID idReacaoResposta;
}
