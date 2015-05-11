package com.danco.training.dobrilko.View.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.IOUtil;
import com.danco.training.dobrilko.View.ioutil.SpecificIOFeaturesUtil;

public class ShowNumberOfExecutedOrdersAction implements IAction {

	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readDate(),
					SpecificIOFeaturesUtil.readDate() };
			String methodName = "getNumberOfExecutedOrders";
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger
					.getLogger(ShowNumberOfExecutedOrdersAction.class);
			logger.error("Input mismatch!");
		}
		return commandMessage;
	}

	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof Integer) {
			IOUtil.print(Integer.toString(((Integer) answerFromServer)));
			IOUtil.println();
		}
	}
}