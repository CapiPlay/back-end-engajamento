package br.senai.sc.engajamento.resposta.model.entity;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoResposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import jakarta.persistence.JoinColumn;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Resposta {
    @Id
    private String idResposta;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dataHora;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_comentario")
    private Comentario idComentario;

    @OneToMany(mappedBy = "idResposta")
    @JsonIgnore
    private List<ReacaoResposta> reacaoRespostaList;

    public Resposta(
            String texto,
            Usuario idUsuario,
            Comentario idComentario
        ) {
        this.idResposta = GeradorUUIDUtils.gerarUuid();
        this.dataHora = ZonedDateTime.now();

        this.texto = texto;
        this.idUsuario = idUsuario;
        this.idComentario = idComentario;
    }
}