package br.senai.sc.engajamento.resposta.model.entity;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class Resposta {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID idResposta;
    @Column(nullable = false)
    private String texto;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dataHora;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Comentario comentario;

    public Resposta(
            String texto,
            Usuario usuario,
            Comentario comentario) {

        this.dataHora = ZonedDateTime.now();

        this.texto = texto;
        this.usuario = usuario;
        this.comentario = comentario;
    }

    //    public Resposta() {
//        this.uuid = GeradorUUIDUtils.gerarUuid();
//    }
}