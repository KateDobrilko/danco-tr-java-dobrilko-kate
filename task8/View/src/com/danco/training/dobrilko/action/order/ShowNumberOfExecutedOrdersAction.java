package com.danco.training.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.command.Command;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ShowNumberOfExecutedOrdersAction implements IAction {

	@Override
	public Command sendCommand() {
		Command command = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readDate(),
					SpecificIOFeaturesUtil.readDate() };
			String methodName = "getNumberOfExecutedOrders";
			command = new Command(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger
					.getLogger(ShowNumberOfExecutedOrdersAction.class);
			logger.error("Input mismatch!");
		}
		return command;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
		if (answerFromServer instanceof Integer) {
			IOUtil.print(Integer.toString(((Integer) answerFromServer)));
			IOUtil.println();
		}
	}
}