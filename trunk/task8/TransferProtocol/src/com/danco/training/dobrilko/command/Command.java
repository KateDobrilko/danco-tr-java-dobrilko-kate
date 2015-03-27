package com.danco.training.dobrilko.command;

public class Command {

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
