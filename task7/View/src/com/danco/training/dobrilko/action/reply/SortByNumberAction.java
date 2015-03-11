package com.danco.training.dobrilko.action.reply;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;

public class SortByNumberAction implements IAction {
	public void execute() {

		BookshopController.sortRepliesByNumber();
	}
}