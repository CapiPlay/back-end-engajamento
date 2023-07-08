package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import br.senai.sc.engajamento.reacoes.model.id.ReacaoComentarioId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletarUmReacaoComentarioCommand {
    private ReacaoComentarioId idReacaoComentario;
}
