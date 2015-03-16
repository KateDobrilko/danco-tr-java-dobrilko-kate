package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;

public class WriteRepliesInFileWithReflectionAction implements IAction{

	
	public void execute() {
		BookshopController.writeRepliesWithReflection();
		
	}

}
