package com.danco.training.dobrilko.action.replysystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.SingletonBookShopController;

public class SortByAlphabetAction implements IAction {
    public void execute() {

	SingletonBookShopController.getInstance().sortRepliesByAlphabet();
    }
}