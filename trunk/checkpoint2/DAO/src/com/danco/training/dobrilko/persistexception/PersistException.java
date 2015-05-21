package com.danco.training.dobrilko.persistexception;

public class PersistException extends Exception {

	private static final long serialVersionUID = -8638202896113748336L;

	public PersistException() {
	}

	public PersistException(String message) {
		super(message);
	}

	public PersistException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistException(Throwable cause) {
		super(cause);
	}

	public PersistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}