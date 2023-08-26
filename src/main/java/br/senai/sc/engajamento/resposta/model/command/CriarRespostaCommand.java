package br.senai.sc.engajamento.resposta.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarRespostaCommand {
    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do comentário não informado")
    private String idComentario;

    @NotNull(message = "Texto da resposta não informado")
    private String texto;

    public CriarRespostaCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}