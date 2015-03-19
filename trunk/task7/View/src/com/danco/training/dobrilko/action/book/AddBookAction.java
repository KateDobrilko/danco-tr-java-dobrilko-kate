package com.danco.training.dobrilko.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class AddBookAction implements IAction {

	

	public void execute() {
		try {
			ControllerHolder.getInstance().getController().addBook(SpecificIOFeaturesUtil.readBook());
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddBookAction.class);
			logger.error("Input mismatch!");
		}
	}
}