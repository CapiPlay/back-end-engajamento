package br.senai.sc.engajamento.reacoes.model.command.reacao;

import br.senai.sc.engajamento.comentario.model.command.DeletarComentarioCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarUmReacaoCommand {
    @NotNull
    private String idUsuario;
    @NotNull
    private String idVideo;

    public BuscarUmReacaoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
