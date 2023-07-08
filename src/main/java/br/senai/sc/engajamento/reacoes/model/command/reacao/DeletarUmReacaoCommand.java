package br.senai.sc.engajamento.reacoes.model.command.reacao;

import br.senai.sc.engajamento.reacoes.model.id.ReacaoId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletarUmReacaoCommand {
    private ReacaoId idReacao;
}
