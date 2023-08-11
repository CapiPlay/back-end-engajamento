package br.senai.sc.engajamento.reacoes.model.command.reacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletarUmReacaoCommand {
    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do vídeo não informado")
    private String idVideo;

    public DeletarUmReacaoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
