package com.danco.training.dobrilko.action.store;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class AddBookAction implements IAction {
	public void execute() {

		BookShopController.addBook(SpecificIOFeaturesUtil.readBook());
	}
}