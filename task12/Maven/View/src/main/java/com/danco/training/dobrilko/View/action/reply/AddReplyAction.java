package com.danco.training.dobrilko.View.action.reply;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.CommandMessage.CommandMessage;
import com.danco.training.dobrilko.View.action.IAction;
import com.danco.training.dobrilko.View.ioutil.SpecificIOFeaturesUtil;

public class AddReplyAction implements IAction {

	public CommandMessage sendCommandMessage() {
		CommandMessage commandMessage = null;
		try {

			Object[] args = { SpecificIOFeaturesUtil.readReply() };
			String methodName = "addReply";
			commandMessage = new CommandMessage(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddReplyAction.class);
			logger.error("Input mismatch!");
		}
		return commandMessage;

	}

	public void receiveAnswer(Object answerFromServer) {
	}
}
