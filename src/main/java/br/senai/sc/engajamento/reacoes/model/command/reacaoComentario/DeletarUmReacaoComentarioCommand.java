package br.senai.sc.engajamento.reacoes.model.command.reacaoComentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarUmReacaoComentarioCommand {
    @NotNull(message = "ID do usuário não informado")
    private UUID idUsuario;

    @NotNull(message = "ID do comentário não informado")
    private UUID idComentario;
}