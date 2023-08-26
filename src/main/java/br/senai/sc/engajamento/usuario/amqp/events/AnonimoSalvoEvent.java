package br.senai.sc.engajamento.usuario.amqp.events;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;

public record AnonimoSalvoEvent(String id) {

    public Usuario criar() {
        return Usuario.criar(this);
    }
}
