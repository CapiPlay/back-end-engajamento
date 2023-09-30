package br.senai.sc.engajamento.inscricao.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuscarUmInscricaoCommand {
    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do canal não informado")
    private String idCanal;

    public BuscarUmInscricaoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}

