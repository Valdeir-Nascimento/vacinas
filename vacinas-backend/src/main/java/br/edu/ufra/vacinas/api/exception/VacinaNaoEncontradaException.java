package br.edu.ufra.vacinas.api.exception;

public class VacinaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public VacinaNaoEncontradaException(String message) {
		super(message);
	}
}
