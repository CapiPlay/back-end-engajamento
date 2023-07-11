package br.senai.sc.engajamento.resposta.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CriarRespostaCommand {

    private String texto;
    private UUID idUsuario;
    private UUID idComentario;

}
