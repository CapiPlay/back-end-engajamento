package br.senai.sc.engajamento.resposta.model.entity;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class Resposta {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @Column(nullable = false)
    private String texto;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_hora;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Comentario comentario;

    public Resposta() {
        this.uuid = GeradorUUIDUtils.gerarUuid();
    }
}
