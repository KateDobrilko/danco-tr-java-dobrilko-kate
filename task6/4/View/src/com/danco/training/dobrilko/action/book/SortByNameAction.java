package com.danco.training.dobrilko.action.book;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;

public class SortByNameAction implements IAction {
	public void execute() {

		BookshopController.sortBookByName();
	}
}