package com.danco.training.dobrilko.View.action.reply;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;

public class SortByAlphabetAction implements IAction {
	private static final String INPUT_MISMATCH = "Input mismatch!";
	private static final String SORT_REPLIES_BY_ALPHABET = "sortRepliesByAlphabet";

	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = {};
			String methodName = SORT_REPLIES_BY_ALPHABET;
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(SortByAlphabetAction.class);
			logger.error(INPUT_MISMATCH);
		}
		return commandMessage;
	}

	public void receiveAnswer(Object answerFromServer) {

	}
}