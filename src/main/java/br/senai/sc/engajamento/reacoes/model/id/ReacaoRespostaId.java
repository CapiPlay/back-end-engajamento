package br.senai.sc.engajamento.reacoes.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReacaoRespostaId implements Serializable {
    private String idUsuario;
    private String idResposta;
}
