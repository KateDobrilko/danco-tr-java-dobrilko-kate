package com.danco.training.dobrilko.action.order;

import java.util.Date;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ShowNumberOfExecutedOrdersAction implements IAction {
	public void execute() {
		Date startDate = SpecificIOFeaturesUtil.readDate();
		Date endDate = SpecificIOFeaturesUtil.readDate();
		IOUtil.print(Integer.toString(BookshopController
				.getNumberOfExecutedOrders(startDate, endDate)));
	}
}