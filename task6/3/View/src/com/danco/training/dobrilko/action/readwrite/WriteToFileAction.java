package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;

public class WriteToFileAction implements IAction {
	public void execute() {
		// IOUtil.print("Path;");
		String path = "src\\com\\danco\\training\\dobrilko\\txt\\temp.out";

		BookShopController.writeInFile(path);
	}
}