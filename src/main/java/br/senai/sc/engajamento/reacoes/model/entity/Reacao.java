package br.senai.sc.engajamento.reacoes.model.entity;

import br.senai.sc.engajamento.reacoes.model.id.ReacaoId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
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
@IdClass(ReacaoId.class)
public class Reacao {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario idUsuario;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_video")
    private Video idVideo;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean curtida;
}
