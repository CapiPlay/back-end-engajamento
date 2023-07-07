package br.senai.sc.engajamento.reacao.model.command;

import br.senai.sc.engajamento.reacao.model.id.ReacaoId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuscarUmReacaoCommand {
    private ReacaoId idReacao;
}
