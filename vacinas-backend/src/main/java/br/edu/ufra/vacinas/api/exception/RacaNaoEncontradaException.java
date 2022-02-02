package br.edu.ufra.vacinas.api.exception;

public class RacaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public RacaNaoEncontradaException(String message) {
        super(message);
    }

    public RacaNaoEncontradaException(Integer idRaca) {
        this(String.format("Não existe cadastro de raça com ID: %d", idRaca));
    }
}
