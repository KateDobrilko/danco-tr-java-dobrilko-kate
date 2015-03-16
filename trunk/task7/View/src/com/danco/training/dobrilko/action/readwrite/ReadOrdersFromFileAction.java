package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;

public class ReadOrdersFromFileAction implements IAction{
	

	@Override
	public void execute() {
		
		BookshopController.readOrdersFromFile();
	}
}
