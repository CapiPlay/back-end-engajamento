package br.senai.sc.engajamento.historico.model.commands;

import br.senai.sc.engajamento.comentario.model.command.CriarComentarioCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarUmHistoricoCommand {

    @NotNull
    private String idUsuario;
    @NotNull
    private String idVideo;

    public BuscarUmHistoricoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
