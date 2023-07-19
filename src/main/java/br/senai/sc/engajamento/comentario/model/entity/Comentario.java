package br.senai.sc.engajamento.comentario.model.entity;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import br.senai.sc.engajamento.video.model.entity.Video;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    @Id
    @Column
    private String idComentario;
    @Column(nullable = false)
    private String texto;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dataHora;
    @Column(nullable = false)
    private Integer qtdRespostas;
    @ManyToOne
    private Usuario idUsuario;
    @ManyToOne
    private Video idVideo;

    @JsonCreator
    public Comentario(
            @JsonProperty("texto") String texto,
            @JsonProperty("idUsuario") Usuario idUsuario,
            @JsonProperty("idVideo") Video idVideo) {

        this.idComentario = GeradorUUIDUtils.gerarUuid();
        this.dataHora = ZonedDateTime.now();
        this.qtdRespostas = 0;

        this.texto = texto;
        this.idUsuario = idUsuario;
        this.idVideo = idVideo;
    }

}
