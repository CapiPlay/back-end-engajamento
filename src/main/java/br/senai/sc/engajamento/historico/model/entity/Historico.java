package br.senai.sc.engajamento.historico.model.entity;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
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
@AllArgsConstructor
@IdClass(HistoricoId.class)
public class Historico {

    @Id
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Usuario idUsuario;

    @Id
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Video idVideo;

    /*Formato no MYSQL 'YYYY-MM-DD HH:MM:SS'*/
    private ZonedDateTime dataHora;

    public Historico(
            Usuario usuario,
            Video video) {
        this.idUsuario = usuario;
        this.idVideo = video;
        this.dataHora = ZonedDateTime.now(UTC);
    }
}