package com.danco.training.dobrilko.action.reply;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;

public class SortByAlphabetAction implements IAction {
	public void execute() {

		BookshopController.sortRepliesByAlphabet();
	}
}