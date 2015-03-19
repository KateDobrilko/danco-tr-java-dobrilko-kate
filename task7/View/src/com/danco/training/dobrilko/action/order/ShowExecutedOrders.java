package com.danco.training.dobrilko.action.order;

import java.util.Date;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class ShowExecutedOrders implements IAction {

	public void execute() {
		IOUtil.print("Start date:");
		Date start = SpecificIOFeaturesUtil.readDate();
		Date end = SpecificIOFeaturesUtil.readDate();
		IOUtil.print(ControllerHolder.getInstance().getController()
				.getExecutedOrdersString(start, end));
		IOUtil.println();

	}
}