package com.danco.training.dobrilko.action.reply;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class SortByNumberAction implements IAction {
	public void execute() {

		ControllerHolder.getInstance().getController().sortRepliesByNumber();
	}
}