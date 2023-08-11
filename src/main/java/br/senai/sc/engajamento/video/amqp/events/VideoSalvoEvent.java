package br.senai.sc.engajamento.video.amqp.events;

public record VideoSalvoEvent(String id, Boolean ehInativado) {}