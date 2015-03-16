package com.danco.training.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.entity.Order;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class AddOrderAction implements IAction {

	public void execute() {
		try {
			Order order = SpecificIOFeaturesUtil.readOrder();
			BookshopController.addOrder(order);
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddOrderAction.class);
			logger.error("Input mismatch!");
		}
	}
}
