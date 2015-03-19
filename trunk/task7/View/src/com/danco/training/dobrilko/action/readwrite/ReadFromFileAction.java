package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.property.PropertyStorage;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class ReadFromFileAction implements IAction {
	

	public void execute() {

		ControllerHolder.getInstance().getController().readFromFile(PropertyStorage.getInstance()
				.getSerializationStoragePath());
	}
}
