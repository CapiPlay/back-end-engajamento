package br.senai.sc.engajamento.video.model.entity;

import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import br.senai.sc.engajamento.video.amqp.events.VideoSalvoEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Video {
    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private Long visualizacao;

    @Column(nullable = false)
    private Boolean ehInativado;

    public Video() {
        this.id = GeradorUUIDUtils.gerarUuid();
    }

    public Video(VideoSalvoEvent event) {
        this.id = event.id();
        this.visualizacao = event.visualizacao();
        this.ehInativado = event.ehInativado();
    }
}