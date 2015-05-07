package com.danco.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class CancelOrderAction implements IAction {

	private static final String INPUT_MISMATCH = "Input mismatch!";
	private static final String CANCEL_ORDER = "cancelOrder";

	@Override
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

	@Override
	public void receiveAnswer(Object answerFromServer) {
	}
}
