package com.danco.training.dobrilko.action.replysystem;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Reply;
import com.danco.training.dobrilko.controller.SingletonBookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class AddReplyAction implements IAction {
    public void execute() {

	Book book = IOUtil.readBook();
	IOUtil.print("Number of replies:");
	int number = IOUtil.readInt();

	SingletonBookShopController.getInstance().addReply(
		new Reply(book, number));
    }
}
