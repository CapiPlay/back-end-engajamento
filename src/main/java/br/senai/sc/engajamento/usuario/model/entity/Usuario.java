package br.senai.sc.engajamento.usuario.model.entity;

import br.senai.sc.engajamento.utils.GeradorUUIDUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class Usuario {
    @Id
    @Column
    private String idUsuario;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String foto;
    @Column(nullable = false)
    private int inscricoes;
    @Column(nullable = false)
    private int inscritos;

    public Usuario() {
        this.idUsuario = GeradorUUIDUtils.gerarUuid();
    }
}
