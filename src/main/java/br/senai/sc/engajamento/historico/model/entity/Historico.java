package br.senai.sc.engajamento.historico.model.entity;

import br.senai.sc.engajamento.model.entity.Usuario;
import br.senai.sc.engajamento.model.entity.Video;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(HistoricoId.class)
public class Historico {

    @ManyToOne
    @Id
    private Usuario usuario;

    @ManyToOne
    @Id
    private Video video;

    /*Formato no MYSQL 'YYYY-MM-DD HH:MM:SS'*/
    private ZonedDateTime dataHora;

    public Historico(
            Usuario usuario,
            Video video) {
        this.usuario = usuario;
        this.video = video;
        this.dataHora = ZonedDateTime.now();
    }
}