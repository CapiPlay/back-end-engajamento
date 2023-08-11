package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletarUmReacaoComentarioCommand {
    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do comentário não informado")
    private String idComentario;

    public DeletarUmReacaoComentarioCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
