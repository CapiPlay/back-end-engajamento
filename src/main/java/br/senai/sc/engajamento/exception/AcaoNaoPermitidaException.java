package br.senai.sc.engajamento.exception;

public class AcaoNaoPermitidaException extends RuntimeException {
    public AcaoNaoPermitidaException() {
        super("Ação não permitida!");
    }
}
