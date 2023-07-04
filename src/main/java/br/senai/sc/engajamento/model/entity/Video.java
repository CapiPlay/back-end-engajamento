package br.senai.sc.engajamento.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

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
