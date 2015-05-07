package com.danco.dobrilko.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.ioutil.IOUtil;

public class DeleteBookAction implements IAction {

	@Override
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

	@Override
	public void receiveAnswer(Object answerFromServer) {
	}
}