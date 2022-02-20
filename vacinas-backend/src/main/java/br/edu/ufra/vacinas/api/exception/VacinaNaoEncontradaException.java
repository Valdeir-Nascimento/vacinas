package br.edu.ufra.vacinas.api.exception;

public class VacinaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public VacinaNaoEncontradaException(String message) {
        super(message);
    }

    public VacinaNaoEncontradaException(Integer id) {
        this(String.format("Não existe registro de Vacina com código: %d", id));
    }

}
