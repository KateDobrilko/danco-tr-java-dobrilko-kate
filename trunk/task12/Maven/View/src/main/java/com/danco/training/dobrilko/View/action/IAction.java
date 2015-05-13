package com.danco.training.dobrilko.View.action;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;

public interface IAction {
	public CommandMessage sendCommandMessage();

	public void receiveAnswer(Object answerFromServer);
}
