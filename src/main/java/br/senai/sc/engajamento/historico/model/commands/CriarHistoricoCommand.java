package br.senai.sc.engajamento.historico.model.commands;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarHistoricoCommand {

    @NotNull
    private String idUsuario;
    @NotNull
    private String idVideo;
    @NotNull
    private float percentagemSomada;

    public CriarHistoricoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
