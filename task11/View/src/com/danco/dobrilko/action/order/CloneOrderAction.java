package com.danco.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;
import com.danco.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class CloneOrderAction implements IAction {

	@Override
	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readId() };
			String methodName = "cloneOrder";
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(CloneOrderAction.class);
			logger.error("Input mismatch!");
		}
		return commandMessage;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
		// TODO Auto-generated method stub

	}
}
