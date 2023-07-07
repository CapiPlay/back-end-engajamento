package br.senai.sc.engajamento.utils;

import java.util.UUID;

public final class GeradorUUIDUtils {

    private GeradorUUIDUtils() {
    }

    public static UUID gerarUuid() {
        return UUID.randomUUID();
    }

}