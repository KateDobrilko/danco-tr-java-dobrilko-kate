package com.danco.training.dobrilko.action.store;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.SingletonBookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ShowAllBooksAction implements IAction {
    public void execute() {
	IOUtil.print(SingletonBookShopController.getInstance().showStore());
    }
}