package com.danco.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.IOUtil;
import com.danco.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ShowExecutedOrders implements IAction {

	@Override
	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readDate(),
					SpecificIOFeaturesUtil.readDate() };
			String methodName = "getExecutedOrdersString";
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(ShowExecutedOrders.class);
			logger.error("Input mismatch!");
		}
		return commandMessage;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof String) {
			IOUtil.print((String) answerFromServer);
			IOUtil.println();
		}

	}
}