package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.property.PropertyStorage;

public class ReadBooksFromFileAction implements IAction {

	@Override
	public void execute() {
		
		BookshopController.readBooksFromFile(PropertyStorage.getInstance().getCSVBookFilePath());
	}
}
