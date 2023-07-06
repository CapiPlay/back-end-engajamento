package br.senai.sc.engajamento.reacaoResposta.model.entity;

import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReacaoRespota {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @ManyToOne
    private Resposta resposta;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean curtida;
}
