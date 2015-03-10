package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.property.PropertyStorage;

public class ReadFromFileAction implements IAction {
	public void execute() {
		
		BookshopController.readFromFile(PropertyStorage.getInstance().getSerializationStoragePath());
	}
}
