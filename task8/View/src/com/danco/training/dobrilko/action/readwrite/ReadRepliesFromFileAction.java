package com.danco.training.dobrilko.action.readwrite;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.command.Command;

public class ReadRepliesFromFileAction implements IAction {

	@Override
	public Command sendCommand() {
		Command command = null;
		try {

			Object[] args = {};
			String methodName = "readRepliesFromFile";
			command = new Command(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(ReadRepliesFromFileAction.class);
			logger.error("Input mismatch!");
		}
		return command;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
	}
}