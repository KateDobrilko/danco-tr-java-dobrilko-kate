package com.danco.training.dobrilko.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.command.Command;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ShowAllBooksAction implements IAction {

	@Override
	public Command sendCommand() {
		Command command = null;
		try {

			Object[] args = {};
			String methodName = "getBooks";
			command = new Command(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(ShowAllBooksAction.class);
			logger.error("Input mismatch!");
		}
		return command;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof String) {
			IOUtil.print((String) answerFromServer);
			IOUtil.println();
		}

	}
}