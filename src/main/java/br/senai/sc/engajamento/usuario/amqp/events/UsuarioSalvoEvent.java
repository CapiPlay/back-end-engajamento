package br.senai.sc.engajamento.usuario.amqp.events;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;

public record UsuarioSalvoEvent(String id, String perfil,
                                String nome, String foto,
                                String descricao) {
    public Usuario editar(Usuario usuario) {
        usuario.editar(this);
        return usuario;
    }

    public Usuario criar() {
        return Usuario.criar(this);
    }
}
