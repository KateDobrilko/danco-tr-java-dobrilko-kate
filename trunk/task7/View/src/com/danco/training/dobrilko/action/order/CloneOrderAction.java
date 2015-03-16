package com.danco.training.dobrilko.action.order;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Order;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class CloneOrderAction implements IAction {

	@Override
	public void execute() {
		try {
			int id = SpecificIOFeaturesUtil.readId();
			Order orderClone = BookshopController.cloneOrder(id);
			IOUtil.print("Add new book:");
			IOUtil.println();
			Book book = SpecificIOFeaturesUtil.readBook();
			orderClone.getBooks().add(book);
			IOUtil.print("Clone:");
			IOUtil.println();
			SpecificIOFeaturesUtil.printOrder(orderClone);
			IOUtil.print("Original:");
			IOUtil.println();
			SpecificIOFeaturesUtil.printOrder(BookshopController
					.getOrderById(id));
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(CloneOrderAction.class);
			logger.error("Input mismatch!");
		}

	}
}
