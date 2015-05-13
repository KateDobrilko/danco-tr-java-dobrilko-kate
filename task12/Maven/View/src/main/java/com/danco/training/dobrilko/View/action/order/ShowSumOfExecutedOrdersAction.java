package com.danco.training.dobrilko.View.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.IOUtil;
import com.danco.training.dobrilko.View.ioutil.SpecificIOFeaturesUtil;

public class ShowSumOfExecutedOrdersAction implements IAction {

	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readDate(),
					SpecificIOFeaturesUtil.readDate() };
			String methodName = "getSumOfExecutedOrders";
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger
					.getLogger(ShowSumOfExecutedOrdersAction.class);
			logger.error("Input mismatch!");
		}
		return commandMessage;
	}

	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof Double) {
			IOUtil.print(Double.toString(((Double) answerFromServer)));
			IOUtil.println();
		}
	}
}