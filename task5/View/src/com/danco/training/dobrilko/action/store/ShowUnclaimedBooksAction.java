package com.danco.training.dobrilko.action.store;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ShowUnclaimedBooksAction implements IAction {
	public void execute() {
		IOUtil.print(BookShopController.showUnclaimedBooks());
	}
}