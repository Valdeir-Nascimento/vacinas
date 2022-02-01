package br.edu.ufra.vacinas.api.exception;

public class AnimalNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public AnimalNaoEncontradoException(String message) {
		super(message);
	}
}
