package br.senai.sc.engajamento.reacoes.model.entity;

import br.senai.sc.engajamento.reacoes.model.id.ReacaoRespostaId;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
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
@IdClass(ReacaoRespostaId.class)
public class ReacaoResposta {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario idUsuario;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_resposta")
    private Resposta idResposta;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean curtida;
}
