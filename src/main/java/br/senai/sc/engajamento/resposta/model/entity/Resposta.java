package br.senai.sc.engajamento.resposta.model.entity;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Resposta {
    @Id
    @Column
    private String idResposta;
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

        this.idResposta = GeradorUUIDUtils.gerarUuid();
        this.dataHora = ZonedDateTime.now();

        this.texto = texto;
        this.usuario = usuario;
        this.comentario = comentario;
    }
}