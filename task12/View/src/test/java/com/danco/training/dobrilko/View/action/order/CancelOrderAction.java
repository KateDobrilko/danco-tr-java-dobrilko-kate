package com.danco.training.dobrilko.View.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.SpecificIOFeaturesUtil;

public class CancelOrderAction implements IAction {

	private static final String INPUT_MISMATCH = "Input mismatch!";
	private static final String CANCEL_ORDER = "cancelOrder";

	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {
			Object[] args = { SpecificIOFeaturesUtil.readId() };
			String methodName = CANCEL_ORDER;
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(CancelOrderAction.class);
			logger.error(INPUT_MISMATCH);
		}
		return commandMessage;
	}

	public void receiveAnswer(Object answerFromServer) {
	}
}
