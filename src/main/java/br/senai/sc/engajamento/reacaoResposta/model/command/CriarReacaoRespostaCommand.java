package br.senai.sc.engajamento.reacaoResposta.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CriarReacaoRespostaCommand {
    private UUID idUsuario;
    private UUID idResposta;
    private boolean curtida;
}
