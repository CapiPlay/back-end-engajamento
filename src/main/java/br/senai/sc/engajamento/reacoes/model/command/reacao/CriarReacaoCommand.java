package br.senai.sc.engajamento.reacoes.model.command.reacao;

import br.senai.sc.engajamento.comentario.model.command.DeletarComentarioCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarReacaoCommand {
    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;

    @NotNull(message = "ID do vídeo não informado")
    private String idVideo;

    @NotNull(message = "Curtida não informada")
    private Boolean curtida;

    public CriarReacaoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
