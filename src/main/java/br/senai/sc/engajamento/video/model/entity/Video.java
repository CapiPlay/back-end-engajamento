package br.senai.sc.engajamento.video.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TVideo")
public class Video {
    @Id
    private UUID uuid;
    @Column(nullable = false)
    private Long visualizacao;
}