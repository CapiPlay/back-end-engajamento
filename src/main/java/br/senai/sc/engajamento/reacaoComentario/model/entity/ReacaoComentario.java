package br.senai.sc.engajamento.reacaoComentario.model.entity;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
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
public class ReacaoComentario {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @ManyToOne
    private Comentario comentario;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean curtida;
}
