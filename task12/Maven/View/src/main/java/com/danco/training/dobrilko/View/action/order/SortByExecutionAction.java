package com.danco.training.dobrilko.View.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.IOUtil;

public class SortByExecutionAction implements IAction {

	public CommandMessage sendCommandMessage() {
		CommandMessage CommandMessage = null;
		try {

			Object[] args = {};
			String methodName = "sortOrdersByExecution";
			CommandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(SortByExecutionAction.class);
			logger.error("Input mismatch!");
		}
		return CommandMessage;
	}

	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof String) {
			IOUtil.print((String) answerFromServer);
			IOUtil.println();
		}
	}
}
