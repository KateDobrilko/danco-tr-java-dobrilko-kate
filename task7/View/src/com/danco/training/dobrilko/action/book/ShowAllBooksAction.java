package com.danco.training.dobrilko.action.book;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class ShowAllBooksAction implements IAction {
	

	public void execute() {

		IOUtil.print(ControllerHolder.getInstance().getController().getBooks());
		IOUtil.println();

	}
}