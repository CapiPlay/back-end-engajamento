package br.senai.sc.engajamento.resposta.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarUmaRespostaCommand {
    @NotNull(message = "ID da resposta não informado")
    private String idResposta;
}