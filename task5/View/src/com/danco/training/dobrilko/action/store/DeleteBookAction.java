package com.danco.training.dobrilko.action.store;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.SingletonBookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class DeleteBookAction implements IAction {
    public void execute() {

	IOUtil.print("Id:");
	int id = IOUtil.readInt();
	SingletonBookShopController.getInstance().deleteBook(id);
    }
}