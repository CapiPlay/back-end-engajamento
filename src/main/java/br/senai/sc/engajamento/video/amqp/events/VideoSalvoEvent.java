package br.senai.sc.engajamento.video.amqp.events;

public record VideoSalvoEvent(String id, Long visualizacao, Boolean ehInativado) {
}
