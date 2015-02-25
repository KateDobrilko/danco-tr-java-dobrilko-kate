package com.danco.training.dobrilko.action.store;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;

public class SortByPublicationDateAction implements IAction {
	public void execute() {

		BookShopController.sortBookByPublicationDate();
	}
}