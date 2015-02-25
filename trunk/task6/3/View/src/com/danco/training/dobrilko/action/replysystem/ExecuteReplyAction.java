package com.danco.training.dobrilko.action.replysystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ExecuteReplyAction implements IAction {
	public void execute() {
		IOUtil.print("Id:");
		int id = IOUtil.readInt();
		BookShopController.executeReply(id);
	}
}
