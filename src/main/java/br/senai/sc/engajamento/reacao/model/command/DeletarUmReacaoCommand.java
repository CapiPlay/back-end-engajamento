package br.senai.sc.engajamento.reacao.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarUmReacaoCommand {
    private UUID idReacao;
}
