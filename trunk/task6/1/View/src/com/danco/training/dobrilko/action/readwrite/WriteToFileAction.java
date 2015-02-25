package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class WriteToFileAction implements IAction {
	public void execute() {
		// IOUtil.print("Store Path;");
		String SPath = "src\\com\\danco\\training\\dobrilko\\txt\\Store.txt";
		// IOUtil.print("Ordering System Path;");
		String OSPath = "src\\com\\danco\\training\\dobrilko\\txt\\OrderingSystem.txt";
		// IOUtil.print("Reply System Path:");
		String RSPath = "src\\com\\danco\\training\\dobrilko\\txt\\ReplySystem.txt";

		BookShopController.writeToFile(SPath, OSPath, RSPath);
	}
}