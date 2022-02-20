package br.edu.ufra.vacinas.api.exception;

public class AnimalNaoEncontradoException extends EntidadeNaoEncontradaException {

    public AnimalNaoEncontradoException(String message) {
        super(message);
    }

    public AnimalNaoEncontradoException(Integer id) {
        this(String.format("Não existe registro de Animal com código: ", id));
    }

}
