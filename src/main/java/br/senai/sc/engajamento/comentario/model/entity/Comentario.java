package br.senai.sc.engajamento.comentario.model.entity;

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
public class Comentario {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @Column(nullable = false)
    private String texto;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_hora;
    @Column(nullable = false)
    private Integer qtd_respostas;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Video video;

    public Comentario() {
        this.uuid = GeradorUUIDUtils.gerarUuid();
    }
}
