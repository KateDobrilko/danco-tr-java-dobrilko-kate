package com.danco.training.dobrilko.action.order;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;

public class SortByPriceAction implements IAction {
	public void execute() {

		BookshopController.sortOrdersByPrice();
	}
}
