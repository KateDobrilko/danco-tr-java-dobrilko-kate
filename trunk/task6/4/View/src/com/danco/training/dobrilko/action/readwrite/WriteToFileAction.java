package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.property.PropertyStorage;

public class WriteToFileAction implements IAction {
	public void execute() {

		BookshopController.writeInFile(PropertyStorage.getInstance().getSerializationStoragePath());
	}
}