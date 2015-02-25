package com.danco.training.dobrilko.action.orderingsystem;

import java.util.ArrayList;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Order;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class AddOrderAction implements IAction {
	public void execute() {
		ArrayList<Book> books = new ArrayList<Book>();
		IOUtil.print("Input id:");
		int id = IOUtil.readInt();
		IOUtil.print("Input number of books:");
		int number = IOUtil.readInt();
		for (int i = 0; i < number; i++) {

			books.add(SpecificIOFeaturesUtil.readBook());
		}

		BookShopController.addOrder(new Order(id, books));
	}

}
