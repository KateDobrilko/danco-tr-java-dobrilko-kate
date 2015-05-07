package com.danco.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.IOUtil;
import com.danco.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ShowNumberOfExecutedOrdersAction implements IAction {

	@Override
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

	@Override
	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof Integer) {
			IOUtil.print(Integer.toString(((Integer) answerFromServer)));
			IOUtil.println();
		}
	}
}