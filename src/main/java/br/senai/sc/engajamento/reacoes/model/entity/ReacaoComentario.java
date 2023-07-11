package br.senai.sc.engajamento.reacoes.model.entity;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoComentarioId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ReacaoComentarioId.class)
public class ReacaoComentario {
    @Id
    @ManyToOne
    private Usuario idUsuario;
    @Id
    @ManyToOne
    private Comentario idComentario;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean curtida;
}
