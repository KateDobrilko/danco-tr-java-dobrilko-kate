package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;
import com.danco.training.dobrilko.property.PropertyStorage;

public class WriteReplyInFileAction implements IAction {

	@Override
	public void execute() {
		
		int id = SpecificIOFeaturesUtil.readId();
		BookshopController.writeReplyToFile(PropertyStorage.getInstance().getCSVReplyFilePath(),
				BookshopController.getReplyById(id));
	}
}