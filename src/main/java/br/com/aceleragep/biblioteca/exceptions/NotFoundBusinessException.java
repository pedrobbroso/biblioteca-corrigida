package br.com.aceleragep.biblioteca.exceptions;

public class NotFoundBusinessException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public NotFoundBusinessException(String message) {
		super(message);
	}

}
