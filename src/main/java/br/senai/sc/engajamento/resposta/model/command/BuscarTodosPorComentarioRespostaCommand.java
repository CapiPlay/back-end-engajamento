package br.senai.sc.engajamento.resposta.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarTodosPorComentarioRespostaCommand {
    @NotNull(message = "ID do comentário não informado")
    private String idComentario;
}