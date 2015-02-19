package com.danco.training.dobrilko.action.store;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.SingletonBookShopController;

public class SortByNameAction implements IAction {
    public void execute() {

	SingletonBookShopController.getInstance().sortBookByName();
    }
}