package br.senai.sc.engajamento.reacoes.model.exception;

import br.senai.sc.engajamento.exception.NaoEncontradoException;

public class NaoEncontradoReacaoException extends NaoEncontradoException {
    public NaoEncontradoReacaoException() {
        super("Reação não encontrada!");
    }
}
