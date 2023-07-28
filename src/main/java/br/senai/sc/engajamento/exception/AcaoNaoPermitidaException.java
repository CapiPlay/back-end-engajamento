package br.senai.sc.engajamento.exception;

public class AcaoNaoPermitidaException extends Exception {
    public AcaoNaoPermitidaException() {
        super("Ação não permitida!");
    }
}
