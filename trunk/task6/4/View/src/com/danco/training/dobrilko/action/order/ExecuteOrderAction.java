package com.danco.training.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ExecuteOrderAction implements IAction {
	public void execute() {
		try {
			int id = SpecificIOFeaturesUtil.readId();
			BookshopController.executeOrder(id);
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(ExecuteOrderAction.class);
			logger.error("Input mismatch!");
		}
	}
}
