package br.edu.ufra.vacinas.api.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}
}
