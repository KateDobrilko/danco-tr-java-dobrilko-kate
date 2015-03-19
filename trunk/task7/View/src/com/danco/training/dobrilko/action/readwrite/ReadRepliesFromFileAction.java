package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class ReadRepliesFromFileAction implements IAction {

	
	public void execute() {

		ControllerHolder.getInstance().getController().readRepliesFromFile();
	}
}