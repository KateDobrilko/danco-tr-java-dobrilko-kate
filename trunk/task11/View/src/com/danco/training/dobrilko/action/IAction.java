package com.danco.training.dobrilko.action;

import com.danco.training.dobrilko.command.Command;

public interface IAction {
	public Command sendCommand();
	public void receiveAnswer(Object answerFromServer);
}
