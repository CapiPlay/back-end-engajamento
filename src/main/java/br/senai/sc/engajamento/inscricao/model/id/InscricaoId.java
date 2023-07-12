package br.senai.sc.engajamento.inscricao.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoId implements Serializable {
    private UUID idUsuario;
    private UUID idCanal;
}
