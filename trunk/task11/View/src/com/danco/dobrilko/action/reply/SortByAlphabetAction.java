package com.danco.dobrilko.action.reply;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;

public class SortByAlphabetAction implements IAction {
	private static final String INPUT_MISMATCH = "Input mismatch!";
	private static final String SORT_REPLIES_BY_ALPHABET = "sortRepliesByAlphabet";

	@Override
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

	@Override
	public void receiveAnswer(Object answerFromServer) {

	}
}