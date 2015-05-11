package com.danco.training.dobrilko.View.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.SpecificIOFeaturesUtil;

public class AddOrderAction implements IAction {

	private static final String INPUT_MISMATCH = "Input mismatch!";
	private static final String ADD_ORDER = "addOrder";

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

	public void receiveAnswer(Object answerFromServer) {
	}
}
