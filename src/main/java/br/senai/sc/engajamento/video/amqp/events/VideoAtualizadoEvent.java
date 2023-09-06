package br.senai.sc.engajamento.video.amqp.events;

public record VideoAtualizadoEvent(
        String id,
        Long qtdCurtida,
        Long qtdComentarios,
        Double pontuacao) {
}
