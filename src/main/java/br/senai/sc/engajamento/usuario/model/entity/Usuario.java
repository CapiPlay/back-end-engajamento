package br.senai.sc.engajamento.usuario.model.entity;

import br.senai.sc.engajamento.usuario.amqp.events.AnonimoSalvoEvent;
import br.senai.sc.engajamento.usuario.amqp.events.UsuarioSalvoEvent;
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

    private String nomePerfil;

    private String nomeCanal;

    private String foto;

    @Column(nullable = false)
    private int quantidadeInscritos;

    @Column(length = 250)
    private String descricao;

    public Usuario editar(UsuarioSalvoEvent event) {
        this.idUsuario = event.id();
        this.nomePerfil = event.nome();
        this.nomeCanal = event.perfil();
        this.foto = event.foto();
        this.descricao = event.descricao();

        return this;
    }

    public static Usuario criar(UsuarioSalvoEvent event) {
        Usuario usuario = new Usuario(event.id());
        usuario.editar(event);
        return usuario;
    }

    public static Usuario criar(AnonimoSalvoEvent event) {
        return new Usuario(event.id());
    }

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

    public Usuario(
            String idUsuario
    ) {
        this.idUsuario = idUsuario;
        this.quantidadeInscritos = 0;
    }
}
