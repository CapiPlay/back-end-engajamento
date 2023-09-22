package br.senai.sc.engajamento.video.amqp.events;

public record VideoAtualizadoEvent(
        String id,
        Long qtdCurtidas,
        Long qtdComentarios,
        Double pontuacao
) {
}
