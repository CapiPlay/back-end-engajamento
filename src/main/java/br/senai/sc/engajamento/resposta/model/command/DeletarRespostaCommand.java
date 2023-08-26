package br.senai.sc.engajamento.resposta.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletarRespostaCommand {
    @NotNull(message = "ID da resposta não informado")
    private String idResposta;

    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    public DeletarRespostaCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}