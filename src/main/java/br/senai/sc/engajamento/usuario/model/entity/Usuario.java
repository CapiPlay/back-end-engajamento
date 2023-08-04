package br.senai.sc.engajamento.usuario.model.entity;

import br.senai.sc.engajamento.usuario.amqp.events.UsuarioSalvoEvent;
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
    private String nomePerfil;
    @Column(nullable = false)
    private String nomeCanal;
    @Column(nullable = false)
    private String foto;
    @Column(nullable = false)
    private int quantidadeInscritos;
    @Column(nullable = false)
    private String descricao;


    public Usuario(String idUsuario,  String nomePerfil,
                   String nomeCanal, String foto,
                   String descricao) {
        this.idUsuario = idUsuario;
        this.nomePerfil = nomePerfil;
        this.nomeCanal = nomeCanal;
        this.foto = foto;
        this.quantidadeInscritos = 0;
        this.descricao = descricao;
    }

    public Usuario(UsuarioSalvoEvent event) {
        this.idUsuario = event.id();
        this.nomePerfil = event.nomePerfil();
        this.nomeCanal = event.nomeCanal();
        this.foto = event.foto();
        this.quantidadeInscritos = 0;
        this.descricao = event.descricao();
    }
}
