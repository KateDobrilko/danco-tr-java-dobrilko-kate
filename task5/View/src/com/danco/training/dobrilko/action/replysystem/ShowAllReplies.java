package com.danco.training.dobrilko.action.replysystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ShowAllReplies implements IAction {
	public void execute() {
		IOUtil.print(BookShopController.showReplies());
	}
}
