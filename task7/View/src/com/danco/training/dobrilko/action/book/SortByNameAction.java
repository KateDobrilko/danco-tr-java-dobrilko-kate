package com.danco.training.dobrilko.action.book;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class SortByNameAction implements IAction {
	
	public void execute() {

		ControllerHolder.getInstance().getController().sortBookByName();
	}
}