package br.senai.sc.engajamento.usuario.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

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
    private int quantidadeInscritos;

    public Usuario(String idUsuario, String nome, String foto) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.foto = foto;
        this.quantidadeInscritos = 0;
    }

}
