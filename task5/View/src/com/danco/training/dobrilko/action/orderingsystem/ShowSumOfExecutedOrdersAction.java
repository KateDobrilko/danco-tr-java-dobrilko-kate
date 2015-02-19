package com.danco.training.dobrilko.action.orderingsystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.SingletonBookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ShowSumOfExecutedOrdersAction implements IAction {
    public void execute() {
	IOUtil.print("Start Year:");
	int startYear = IOUtil.readInt();
	IOUtil.print("Start Month:");
	int startMonth = IOUtil.readInt();
	IOUtil.print("End Year:");
	int endYear = IOUtil.readInt();
	IOUtil.print("End Month:");
	int endMonth = IOUtil.readInt();
	IOUtil.print(SingletonBookShopController.getInstance()
		.showSumOfExecutedOrders(startYear, startMonth, endYear,
			endMonth));
    }
}