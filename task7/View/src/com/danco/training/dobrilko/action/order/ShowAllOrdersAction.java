package com.danco.training.dobrilko.action.order;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.entitiy.Order;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ShowAllOrdersAction implements IAction {
	public void execute() {
		for (Order order : BookshopController.getOrders()) {
			SpecificIOFeaturesUtil.printOrder(order);
			IOUtil.println();
		}

	}
}
