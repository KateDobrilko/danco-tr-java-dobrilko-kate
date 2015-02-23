package com.danco.training.dobrilko.action.replysystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Reply;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class AddReplyAction implements IAction {
	public void execute() {

		Book book = SpecificIOFeaturesUtil.readBook();
		IOUtil.print("Number of replies:");
		int number = IOUtil.readInt();

		BookShopController.addReply(new Reply(book, number));
	}
}
