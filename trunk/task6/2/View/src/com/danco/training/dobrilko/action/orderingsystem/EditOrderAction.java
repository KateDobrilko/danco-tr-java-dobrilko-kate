package com.danco.training.dobrilko.action.orderingsystem;

import java.util.ArrayList;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Order;
import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class EditOrderAction implements IAction {

	@Override
	public void execute() {
		ArrayList<Book> books = new ArrayList<Book>();
		IOUtil.print("Input id of order:");
		int id = IOUtil.readInt();
		Order orderClone = new Order();
		Order order = new Order();
		try {
			orderClone = BookShopController.getInstance().getOrderingSystem().getOrder(id).clone();
			order = BookShopController.getInstance().getOrderingSystem().getOrder(id);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IOUtil.print("Input changes:");
		IOUtil.print("Input number of books:");
		int number = IOUtil.readInt();
		for (int i = 0; i < number; i++) {

			books.add(SpecificIOFeaturesUtil.readBook());
			order.setBooks(books);
			

		}
		IOUtil.print("Apply changes?");
		boolean var = Boolean.parseBoolean(IOUtil.readString());
		if (var) {
			order = orderClone;
		}
	}
}
