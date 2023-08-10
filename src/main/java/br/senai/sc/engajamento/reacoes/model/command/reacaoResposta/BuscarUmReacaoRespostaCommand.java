package br.senai.sc.engajamento.reacoes.model.command.reacaoResposta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarUmReacaoRespostaCommand {
    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID da resposta não informado")
    private String idResposta;
}