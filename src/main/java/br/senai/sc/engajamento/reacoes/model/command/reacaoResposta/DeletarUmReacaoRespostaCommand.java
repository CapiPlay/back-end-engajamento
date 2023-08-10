package br.senai.sc.engajamento.reacoes.model.command.reacaoResposta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarUmReacaoRespostaCommand {
    @NotNull(message = "ID do usuário não informado")
    private UUID idUsuario;

    @NotNull(message = "ID da resposta não informado")
    private UUID idResposta;
}