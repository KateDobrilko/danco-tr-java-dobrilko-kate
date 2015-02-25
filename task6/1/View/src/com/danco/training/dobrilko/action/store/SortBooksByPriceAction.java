package com.danco.training.dobrilko.action.store;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;

public class SortBooksByPriceAction implements IAction {
	public void execute() {

		BookShopController.sortBookByPrice();
	}
}