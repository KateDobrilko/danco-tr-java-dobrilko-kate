package com.danco.training.dobrilko.View.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.IOUtil;

public class SortBooksByPriceAction implements IAction {

	

	private static final String INPUT_MISMATCH = "Input mismatch!";
	private static final String SORT_BOOK_BY_PRICE = "sortBookByPrice";

	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = {};
			String methodName = SORT_BOOK_BY_PRICE;
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(SortBooksByPriceAction.class);
			logger.error(INPUT_MISMATCH);
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