package br.senai.sc.engajamento.reacoes.model.command.reacaoResposta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletarUmReacaoRespostaCommand {

    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID da resposta não informado")
    private String idResposta;

    public DeletarUmReacaoRespostaCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
