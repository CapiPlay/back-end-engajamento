package br.senai.sc.engajamento.video.model.entity;

import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class Video {
    @Id
    @Column
    private String idVideo;
    @Column(nullable = false)
    private Long visualizacao;

    public Video() {
        this.idVideo = GeradorUUIDUtils.gerarUuid();
    }
}