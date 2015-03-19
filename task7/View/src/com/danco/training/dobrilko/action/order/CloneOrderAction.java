package com.danco.training.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class CloneOrderAction implements IAction {
	
	public void execute() {
		try {
			int id = SpecificIOFeaturesUtil.readId();
			ControllerHolder.getInstance().getController().cloneOrder(id);
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(CloneOrderAction.class);
			logger.error("Input mismatch!");
		}

	}
}
