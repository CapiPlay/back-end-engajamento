package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CriarReacaoComentarioCommand {
    private UUID idUsuario;
    private UUID idComentario;
    private boolean curtida;
}
