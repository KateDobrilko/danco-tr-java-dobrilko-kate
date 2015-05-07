package com.danco.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ExecuteOrderAction implements IAction {

	@Override
	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readId() };
			String methodName = "executeOrder";
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(ExecuteOrderAction.class);
			logger.error("Input mismatch!");
		}
		return commandMessage;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
	}
}
