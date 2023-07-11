package br.senai.sc.engajamento.reacoes.model.entity;

import br.senai.sc.engajamento.reacoes.model.id.ReacaoRespostaId;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ReacaoRespostaId.class)
public class ReacaoRespota {
    @Id
    @ManyToOne
    private Usuario idUsuario;
    @Id
    @ManyToOne
    private Resposta idResposta;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean curtida;
}
