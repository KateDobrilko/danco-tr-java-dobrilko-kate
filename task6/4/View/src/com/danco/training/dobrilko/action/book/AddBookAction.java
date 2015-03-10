package com.danco.training.dobrilko.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class AddBookAction implements IAction {
	public void execute() {
		try {
			BookshopController.addBook(SpecificIOFeaturesUtil.readBook());
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddBookAction.class);
			logger.error("Input mismatch!");
		}
	}
}