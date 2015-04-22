package com.danco.training.dobrilko.action.readwrite;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.command.Command;
import com.danco.training.dobrilko.property.PropertyStorage;

public class ReadFromFileAction implements IAction {

	@Override
	public Command sendCommand() {
		Command command = null;
		try {

			Object[] args = { PropertyStorage.getInstance()
					.getSerializationStoragePath() };
			String methodName = "readFromFile";
			command = new Command(methodName, args);

		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(ReadFromFileAction.class);
			logger.error("Input mismatch!");
		}
		return command;
	}

	@Override
	public void receiveAnswer(Object answerFromServer) {
		// TODO Auto-generated method stub

	}
}
