package com.danco.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class AddOrderAction implements IAction {

	private static final String INPUT_MISMATCH = "Input mismatch!";
	private static final String ADD_ORDER = "addOrder";

	@Override
	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readOrder() };
			String methodName = ADD_ORDER;
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddOrderAction.class);
			logger.error(INPUT_MISMATCH);
		}
		return commandMessage;

	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
	}
}
