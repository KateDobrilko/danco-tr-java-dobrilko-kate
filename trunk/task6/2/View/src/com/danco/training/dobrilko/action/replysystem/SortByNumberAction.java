package com.danco.training.dobrilko.action.replysystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;

public class SortByNumberAction implements IAction {
	public void execute() {

		BookShopController.sortRepliesByNumber();
	}
}