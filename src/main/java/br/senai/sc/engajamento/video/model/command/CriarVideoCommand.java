package br.senai.sc.engajamento.video.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriarVideoCommand {
    private Long visualizacao;
}
