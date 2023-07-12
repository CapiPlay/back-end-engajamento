package br.senai.sc.engajamento.historico.model.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class CriarHistoricoCommand {

    private UUID idUsuario;

    private UUID idVideo;
}
