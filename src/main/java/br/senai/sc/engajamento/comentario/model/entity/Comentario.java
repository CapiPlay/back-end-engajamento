package br.senai.sc.engajamento.comentario.model.entity;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import br.senai.sc.engajamento.video.model.entity.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    @Id
    private String idComentario;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dataHora;

    @Column(nullable = false)
    private Integer qtdRespostas;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Usuario idUsuario;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Video idVideo;

    public Comentario(
            String texto,
            Usuario idUsuario,
            Video idVideo) {

        this.idComentario = GeradorUUIDUtils.gerarUuid();
        this.dataHora = ZonedDateTime.now(UTC);
        this.qtdRespostas = 0;

        this.texto = texto;
        this.idUsuario = idUsuario;
        this.idVideo = idVideo;
    }
}
