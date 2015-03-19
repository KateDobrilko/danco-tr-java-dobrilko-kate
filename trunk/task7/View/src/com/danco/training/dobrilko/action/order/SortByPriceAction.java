package com.danco.training.dobrilko.action.order;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class SortByPriceAction implements IAction {
	
	public void execute() {

		ControllerHolder.getInstance().getController().sortOrdersByPrice();
	}
}
