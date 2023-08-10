package br.senai.sc.engajamento.inscricao.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuscarUmInscricaoCommand {
    @NotNull
    private String idUsuario;
    @NotNull
    private String idCanal;

    public BuscarUmInscricaoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
