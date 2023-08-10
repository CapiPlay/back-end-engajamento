package br.senai.sc.engajamento.reacoes.model.command.reacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarUmReacaoCommand {
    @NotNull(message = "ID do usuário não informado")
    private UUID idUsuario;

    @NotNull(message = "ID do vídeo não informado")
    private UUID idVideo;
}