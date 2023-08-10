package br.senai.sc.engajamento.reacoes.model.command.reacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletarUmReacaoCommand {
    @NotNull
    private String idUsuario;
    @NotNull
    private String idVideo;

    public DeletarUmReacaoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
