package com.danco.dobrilko.action.reply;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;

public class SortByNumberAction implements IAction {

	@Override
	public CommandMessage sendCommandMessage() {
		CommandMessage CommandMessage = null;
		try {

			Object[] args = {};
			String methodName = "sortRepliesByNumber";
			CommandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(SortByNumberAction.class);
			logger.error("Input mismatch!");
		}
		return CommandMessage;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {

	}
}