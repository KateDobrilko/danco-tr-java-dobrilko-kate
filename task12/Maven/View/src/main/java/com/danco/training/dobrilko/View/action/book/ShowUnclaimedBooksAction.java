package com.danco.training.dobrilko.View.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.IOUtil;

public class ShowUnclaimedBooksAction implements IAction {

	private static final String GET_UNCLAIMED_BOOKS = "getUnclaimedBooks";

	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = {};
			String methodName = GET_UNCLAIMED_BOOKS;
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(ShowUnclaimedBooksAction.class);
			logger.error("Input mismatch!");
		}
		return commandMessage;
	}

	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof String) {
			IOUtil.print((String) answerFromServer);
			IOUtil.println();
		}
	}
}