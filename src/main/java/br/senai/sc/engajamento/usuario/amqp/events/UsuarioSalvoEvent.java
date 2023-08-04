package br.senai.sc.engajamento.usuario.amqp.events;

public record UsuarioSalvoEvent(String id, String nomePerfil,
                                String nomeCanal, String foto,
                                String descricao) {
}
