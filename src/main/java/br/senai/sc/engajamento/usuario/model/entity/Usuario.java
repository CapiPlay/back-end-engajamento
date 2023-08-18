package br.senai.sc.engajamento.usuario.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Usuario {
    @Id
    @Column
    private String idUsuario;

    @Column(nullable = false)
    private String nomePerfil;

    @Column(nullable = false)
    private String nomeCanal;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private int quantidadeInscritos;

    @Column(nullable = false)
    private String descricao;

    /*Se o usuário estiver inativado, o token não será enviado. Logo, não é necessário este atributo*/
    public Usuario(
            String idUsuario, 
            String nomePerfil,
            String nomeCanal, 
            String foto,
            String descricao
        ) {
        this.idUsuario = idUsuario;
        this.nomePerfil = nomePerfil;
        this.nomeCanal = nomeCanal;
        this.foto = foto;
        this.quantidadeInscritos = 0;
        this.descricao = descricao;
    }
}
