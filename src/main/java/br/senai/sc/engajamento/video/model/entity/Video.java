package br.senai.sc.engajamento.video.model.entity;

import br.senai.sc.engajamento.video.amqp.events.VideoSalvoEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private Long visualizacao;

    @Column(nullable = false)
    private Boolean ehInativado;

    public Video(VideoSalvoEvent event) {
        this.id = event.id();
        this.ehInativado = event.ehInativado();
    }
}