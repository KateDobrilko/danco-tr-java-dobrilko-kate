package com.danco.training.dobrilko.action.orderingsystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class CancelOrderAction implements IAction {
	public void execute() {
		IOUtil.print("Id:");
		int id = IOUtil.readInt();
		BookShopController.cancelOrder(id);
	}
}
