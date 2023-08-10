package br.senai.sc.engajamento.reacoes.model.command.reacaoResposta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletarUmReacaoRespostaCommand {
    @NotNull
    private String idUsuario;
    @NotNull
    private String idResposta;

    public DeletarUmReacaoRespostaCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
