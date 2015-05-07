package com.danco.dobrilko.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.IOUtil;

public class ShowAllBooksAction implements IAction {

	@Override
	public CommandMessage sendCommandMessage() {
		CommandMessage CommandMessage = null;
		try {

			Object[] args = {};
			String methodName = "getBooks";
			CommandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(ShowAllBooksAction.class);
			logger.error("Input mismatch!");
		}
		return CommandMessage;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof String) {
			IOUtil.print((String) answerFromServer);
			IOUtil.println();
		}

	}
}