package br.senai.sc.engajamento.utils;

import java.util.UUID;

public final class GeradorUUIDUtils {

    public static String gerarUuid() {
        return UUID.randomUUID().toString();
    }

}