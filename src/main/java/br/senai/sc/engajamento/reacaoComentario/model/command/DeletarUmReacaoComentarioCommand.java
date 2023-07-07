package br.senai.sc.engajamento.reacaoComentario.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarUmReacaoComentarioCommand {
    private UUID idReacaoComentario;
}
