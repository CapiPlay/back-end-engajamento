package br.senai.sc.engajamento.inscricao.model.command;

import br.senai.sc.engajamento.inscricao.model.id.InscricaoId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriarInscricaoCommand {
    private InscricaoId idReacao;
}
