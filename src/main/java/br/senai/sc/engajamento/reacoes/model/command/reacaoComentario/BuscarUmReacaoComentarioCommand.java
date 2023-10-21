package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscarUmReacaoComentarioCommand {
//    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do comentário não informado")
    private String idComentario;

    public BuscarUmReacaoComentarioCommand(String idUsuario, String idComentario) {
        this.idComentario = idComentario;
        this.idUsuario = idUsuario;
    }

    public BuscarUmReacaoComentarioCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
