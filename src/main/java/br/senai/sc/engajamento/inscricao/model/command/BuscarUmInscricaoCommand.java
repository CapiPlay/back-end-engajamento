package br.senai.sc.engajamento.inscricao.model.command;

import br.senai.sc.engajamento.inscricao.model.id.InscricaoId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuscarUmInscricaoCommand {
    @NotNull
    private InscricaoId idReacao;
}
