package com.danco.training.dobrilko.CommandMessage;

import java.io.Serializable;

public class CommandMessage implements Serializable {
	
	private static final long serialVersionUID = 4274219402327561088L;
	private String nameOfMethodToCall;
	private Object[] args;

	public CommandMessage(String nameOfMethodToCall, Object[] args) {
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
