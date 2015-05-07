package com.danco.dobrilko.action;

import com.danco.dobrilko.commandmessage.CommandMessage;

public interface IAction {
	public CommandMessage sendCommandMessage();

	public void receiveAnswer(Object answerFromServer);
}
