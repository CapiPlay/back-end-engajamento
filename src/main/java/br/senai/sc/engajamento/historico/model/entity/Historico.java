package br.senai.sc.engajamento.historico.model.entity;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import br.senai.sc.engajamento.video.model.entity.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class Historico {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Video video;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_hora;

    public Historico() {
        this.uuid = GeradorUUIDUtils.gerarUuid();
    }
}