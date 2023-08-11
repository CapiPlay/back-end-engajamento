package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarReacaoComentarioCommand {

    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do comentário não informado")
    private String idComentario;

    @NotNull(message = "Curtida não informada")
    private Boolean curtida;

    public CriarReacaoComentarioCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
