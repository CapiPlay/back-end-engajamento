package br.senai.sc.engajamento.reacoes.model.entity;

import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class ReacaoRespota {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Resposta resposta;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean curtida;

    public ReacaoRespota() {
        this.uuid = GeradorUUIDUtils.gerarUuid();
    }
}
