package br.senai.sc.engajamento.inscricao.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoId implements Serializable {
    private Long usuario;
    private Long canal;
}
