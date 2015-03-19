package com.danco.training.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class AddOrderAction implements IAction {

	public void execute() {
		try {
			String order = SpecificIOFeaturesUtil.readOrder();
			ControllerHolder.getInstance().getController().addOrder(order);
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddOrderAction.class);
			logger.error("Input mismatch!");
		}
	}
}
