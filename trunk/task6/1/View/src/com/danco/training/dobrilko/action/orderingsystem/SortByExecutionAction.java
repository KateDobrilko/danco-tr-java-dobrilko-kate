package com.danco.training.dobrilko.action.orderingsystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;

public class SortByExecutionAction implements IAction {
	public void execute() {

		BookShopController.sortOrdersByExecution();
	}
}
