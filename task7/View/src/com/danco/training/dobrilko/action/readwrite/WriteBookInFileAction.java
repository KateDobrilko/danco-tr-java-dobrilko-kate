package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;
import com.danco.training.dobrilko.property.PropertyStorage;

public class WriteBookInFileAction implements IAction {

	@Override
	public void execute() {
		
		int id = SpecificIOFeaturesUtil.readId();
		BookshopController.writeBookToFile(PropertyStorage.getInstance().getCSVBookFilePath(),
				BookshopController.getBookById(id));
	}
}
