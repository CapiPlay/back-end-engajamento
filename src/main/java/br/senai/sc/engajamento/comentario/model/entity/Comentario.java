package br.senai.sc.engajamento.comentario.model.entity;

import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import br.senai.sc.engajamento.video.model.entity.Video;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;

@Entity
@Data
@NoArgsConstructor
public class Comentario {

    @Id
    private String idComentario;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dataHora;

    @Column(nullable = false)
    private Integer qtdRespostas;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_video")
    private Video idVideo;

    @OneToMany(mappedBy = "idComentario")
    private List<ReacaoComentario> reacaoComentarioList;

    @OneToMany(mappedBy = "idComentario")
    private List<Resposta> respostas;

    public Comentario(
            String texto,
            Usuario idUsuario,
            Video idVideo) {

        this.idComentario = GeradorUUIDUtils.gerarUuid();
        this.dataHora = ZonedDateTime.now(UTC);
        this.qtdRespostas = 0;

        this.texto = texto;
        this.idUsuario = idUsuario;
        this.idVideo = idVideo;
    }
}
