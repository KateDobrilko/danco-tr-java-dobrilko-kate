package com.danco.training.dobrilko.action.book;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.entitiy.Book;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ShowAllBooksAction implements IAction {
	public void execute() {
		for (Book book : BookshopController.getBooks()) {
			SpecificIOFeaturesUtil.printBook(book);
			IOUtil.println();
		}
	}
}