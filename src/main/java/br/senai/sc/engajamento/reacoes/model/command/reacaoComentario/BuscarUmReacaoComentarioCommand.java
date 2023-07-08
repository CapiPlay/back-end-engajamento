package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BuscarUmReacaoComentarioCommand {
    private UUID idReacaoComentario;
}
