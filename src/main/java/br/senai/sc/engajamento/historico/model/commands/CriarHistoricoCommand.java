package br.senai.sc.engajamento.historico.model.commands;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarHistoricoCommand {
    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do vídeo não informado")
    private String idVideo;

    @NotNull(message = "Percentagem somada não informada")
    private float percentagemSomada;
}