package com.danco.training.dobrilko.action.orderingsystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.SingletonBookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ExecuteOrderAction implements IAction {
    public void execute() {
	IOUtil.print("Id:");
	int id = IOUtil.readInt();
	SingletonBookShopController.getInstance().executeOrder(id);
    }
}
