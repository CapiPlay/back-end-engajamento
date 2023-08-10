package br.senai.sc.engajamento.resposta.model.entity;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import jakarta.persistence.JoinColumn;

import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Resposta {
    @Id
    private String idResposta;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dataHora;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Usuario idUsuario;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Comentario idComentario;

    public Resposta(
            String texto,
            Usuario idUsuario,
            Comentario idComentario
        ) {
        this.idResposta = GeradorUUIDUtils.gerarUuid();
        this.dataHora = ZonedDateTime.now();

        this.texto = texto;
        this.idUsuario = idUsuario;
        this.idComentario = idComentario;
    }
}