package com.danco.training.dobrilko.action.orderingsystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ShowAllOrdersAction implements IAction {
	public void execute() {
		IOUtil.print(BookShopController.showOrders());
	}
}
