package br.senai.sc.engajamento.reacoes.model.entity;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoComentarioId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ReacaoComentarioId.class)
public class ReacaoComentario {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario idUsuario;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_comentario")
    @JsonIgnore
    private Comentario idComentario;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean curtida;
}
