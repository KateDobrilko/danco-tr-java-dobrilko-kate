package com.danco.training.dobrilko.action.replysystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;

public class SortByAlphabetAction implements IAction {
	public void execute() {

		BookShopController.sortRepliesByAlphabet();
	}
}