package br.senai.sc.engajamento.historico.model.entity;

import br.senai.sc.engajamento.model.entity.Usuario;
import br.senai.sc.engajamento.model.entity.Video;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "THistorico")
public class Historico {
    @Id
    private UUID uuid;
    @ManyToOne
    @Column(nullable = false)
    private Usuario usuario;
    @ManyToOne
    @Column(nullable = false)
    private Video video;
}