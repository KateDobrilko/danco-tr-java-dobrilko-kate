package com.danco.training.dobrilko.action.replysystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.SingletonBookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ExecuteReplyAction implements IAction {
    public void execute() {
	IOUtil.print("Id:");
	int id = IOUtil.readInt();
	SingletonBookShopController.getInstance().executeReply(id);
    }
}
