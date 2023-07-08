package br.senai.sc.engajamento.reacoes.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReacaoId implements Serializable {
    private UUID idUsuario;
    private UUID idVideo;
}
