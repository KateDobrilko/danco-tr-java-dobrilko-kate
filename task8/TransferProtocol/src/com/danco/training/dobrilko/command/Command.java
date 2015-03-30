package com.danco.training.dobrilko.command;

import java.io.Serializable;

public class Command implements Serializable {

	private static final long serialVersionUID = 2999615629318292587L;
	private String nameOfMethodToCall;
	private Object[] args;

	public Command(String nameOfMethodToCall, Object[] args) {
		this.nameOfMethodToCall = nameOfMethodToCall;
		this.args = args;
	}

	public String getNameOfMethodToCall() {
		return nameOfMethodToCall;
	}

	public Object[] getArgs() {
		return args;
	}

}
