package com.danco.dobrilko.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.IOUtil;

public class SortByPublicationDateAction implements IAction {

	private static final String INPUT_MISMATCH = "Input mismatch!";
	private static final String SORT_BOOK_BY_PUBLICATION_DATE = "sortBookByPublicationDate";

	@Override
	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = {};
			String methodName = SORT_BOOK_BY_PUBLICATION_DATE;
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(SortByPublicationDateAction.class);
			logger.error(INPUT_MISMATCH);
		}
		return commandMessage;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof String) {
			IOUtil.print((String) answerFromServer);
			IOUtil.println();
		}
	}
}