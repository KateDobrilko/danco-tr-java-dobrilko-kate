package com.danco.dobrilko.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class AddBookAction implements IAction {

	private static final String ADD_BOOK = "addBook";
	private static final String INPUT_MISMATCH = "Input mismatch!";

	@Override
	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readBook() };
			String methodName = ADD_BOOK;
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddBookAction.class);
			logger.error(INPUT_MISMATCH);
		}
		return commandMessage;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
	}

	
}