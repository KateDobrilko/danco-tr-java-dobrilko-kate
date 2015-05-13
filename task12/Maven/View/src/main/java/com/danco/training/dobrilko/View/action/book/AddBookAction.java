package com.danco.training.dobrilko.View.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.SpecificIOFeaturesUtil;

public class AddBookAction implements IAction {

	private static final String ADD_BOOK = "addBook";
	private static final String INPUT_MISMATCH = "Input mismatch!";

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

	public void receiveAnswer(Object answerFromServer) {
	}

	
}