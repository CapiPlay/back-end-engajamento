package br.senai.sc.engajamento.historico.model.entity;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

@Data
@Entity
@NoArgsConstructor
@IdClass(HistoricoId.class)
public class Historico {

    @Id
    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario idUsuario;

    @Id
    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(nullable = false, name = "id_video")
    private Video idVideo;
    /*Formato no MYSQL 'YYYY-MM-DD HH:MM:SS'*/
    private ZonedDateTime dataHora;
    private Integer qtdVisualizadas;
    private float percentagemSomada;

    public Historico(
            Usuario usuario,
            Video video,
            float percentagemSomada) {
        this.idUsuario = usuario;
        this.idVideo = video;
        this.qtdVisualizadas = 1;
        this.percentagemSomada = percentagemSomada;
        this.dataHora = ZonedDateTime.now(UTC);
    }
}