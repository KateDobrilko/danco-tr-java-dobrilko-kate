package com.danco.training.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.command.Command;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class CancelOrderAction implements IAction {

	@Override
	public Command sendCommand() {
		Command command = null;
		try {
			Object[] args = { SpecificIOFeaturesUtil.readId() };
			String methodName = "cancelOrder";
			command = new Command(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(CancelOrderAction.class);
			logger.error("Input mismatch!");
		}
		return command;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
	}
}
