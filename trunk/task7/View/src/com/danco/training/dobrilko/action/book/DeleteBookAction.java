package com.danco.training.dobrilko.action.book;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class DeleteBookAction implements IAction {

	public void execute() {
		try {
			IOUtil.print("Id:");
			int id = IOUtil.readInt();
			ControllerHolder.getInstance().getController().deleteBook(id);
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(DeleteBookAction.class);
			logger.error("Input mismatch!");
		}
	}
}