package com.danco.training.dobrilko.View.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.IOUtil;

public class DeleteBookAction implements IAction {

	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {
			IOUtil.print("Id:");
			Object[] args = { IOUtil.readInt() };
			String methodName = "deleteBook";
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(DeleteBookAction.class);
			logger.error("Input mismatch!");
		}
		return commandMessage;

	}

	public void receiveAnswer(Object answerFromServer) {
	}
}