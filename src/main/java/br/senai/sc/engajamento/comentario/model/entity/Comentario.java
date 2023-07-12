package br.senai.sc.engajamento.comentario.model.entity;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import br.senai.sc.engajamento.video.model.entity.Video;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Comentario {

    @Id
    @Column(columnDefinition = "char(36)")
    private UUID idComentario;
    @Column(nullable = false)
    private String texto;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dataHora;
    @Column(nullable = false)
    private Integer qtdRespostas;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Video video;

    public Comentario(
            String texto,
            Usuario usuario,
            Video video) {

        this.idComentario = GeradorUUIDUtils.gerarUuid();
        this.dataHora = ZonedDateTime.now();
        this.qtdRespostas = 0;

        this.texto = texto;
        this.usuario = usuario;
        this.video = video;
    }

}
