package br.senai.sc.engajamento.resposta.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarRespostaCommand {

    private UUID idResposta;
    private UUID idUsuario;
}
