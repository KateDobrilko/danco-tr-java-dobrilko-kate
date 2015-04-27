package com.danco.training.dobrilko.menu;

import com.danco.training.dobrilko.action.book.*;
import com.danco.training.dobrilko.action.order.*;
import com.danco.training.dobrilko.action.reply.*;
import com.danco.training.dobrilko.constantstorage.ConstantStorage;

public class MenuCreator {

	private static Menu mainMenu = new Menu();
	private static Menu orderingSystemMenu = new Menu();
	private static Menu replySystemMenu = new Menu();
	private static Menu storeMenu = new Menu();

	public static void createMenu() {

		mainMenu.addMenuItem(new MenuItem(null,
				ConstantStorage.OPEN_ORDERING_SYSTEM, orderingSystemMenu));
		mainMenu.addMenuItem(new MenuItem(null,
				ConstantStorage.OPEN_REPLY_SYSTEM, replySystemMenu));
		mainMenu.addMenuItem(new MenuItem(null,
				ConstantStorage.OPEN_STORE_SYSTEM, storeMenu));
		mainMenu.addMenuItem(new MenuItem(null, ConstantStorage.EXIT_MM, null));

		orderingSystemMenu.addMenuItem(new MenuItem(new AddOrderAction(),
				ConstantStorage.ADD_ORDER, orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new CancelOrderAction(),
				ConstantStorage.CANCEL_ORDER, orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new ExecuteOrderAction(),
				ConstantStorage.EXECUTE_ORDER, orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new ShowAllOrdersAction(),
				ConstantStorage.SHOW_ALL_ORDERS, orderingSystemMenu));
		orderingSystemMenu
				.addMenuItem(new MenuItem(
						new ShowNumberOfExecutedOrdersAction(),
						ConstantStorage.SHOW_NUM_OF_EXECUTED_ORDERS,
						orderingSystemMenu));
		orderingSystemMenu
				.addMenuItem(new MenuItem(new ShowSumOfExecutedOrdersAction(),
						ConstantStorage.SHOW_SUM_OF_EXECUTED_ORDERS,
						orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new SortByDateAction(),
				ConstantStorage.SORT_ORDERS_BY_DATE, orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(
				new SortByExecutionAction(),
				ConstantStorage.SORT_ORDERS_BY_EXECUTION, orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new SortByPriceAction(),
				ConstantStorage.SORT_ORDERS_BY_PRICE, orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new CloneOrderAction(),
				ConstantStorage.CLONE_ORDER, orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(null,
				ConstantStorage.OPEN_MAIN_MENU_ORDER, mainMenu));

		replySystemMenu.addMenuItem(new MenuItem(new AddReplyAction(),
				ConstantStorage.ADD_REPLY, replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(new ShowAllReplies(),
				ConstantStorage.SHOW_REPLIES, replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(new SortByAlphabetAction(),
				ConstantStorage.SORT_REPLIES_BY_ALPHABET, replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(new SortByNumberAction(),
				ConstantStorage.SORT_REPLIES_BY_NUMBER, replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(null,
				ConstantStorage.OPEN_MAIN_MENU_REPLY, mainMenu));

		storeMenu.addMenuItem(new MenuItem(new AddBookAction(),
				ConstantStorage.ADD_BOOK, storeMenu));
		storeMenu.addMenuItem(new MenuItem(new DeleteBookAction(),
				ConstantStorage.DELETE_BOOK, storeMenu));
		storeMenu.addMenuItem(new MenuItem(new ShowAllBooksAction(),
				ConstantStorage.SHOW_ALL_BOOKS, storeMenu));
		storeMenu.addMenuItem(new MenuItem(new ShowUnclaimedBooksAction(),
				ConstantStorage.SHOW_UNCLAIMED_BOOKS, storeMenu));
		storeMenu.addMenuItem(new MenuItem(new SortBooksByPriceAction(),
				ConstantStorage.SORT_BOOKS_BY_PRICE, storeMenu));
		storeMenu.addMenuItem(new MenuItem(new SortByNameAction(),
				ConstantStorage.SORT_BOOKS_BY_NAME, storeMenu));
		storeMenu.addMenuItem(new MenuItem(new SortByPublicationDateAction(),
				ConstantStorage.SORT_BOOKS_BY_PUBLICATION_DATE, storeMenu));
		storeMenu.addMenuItem(new MenuItem(null,
				ConstantStorage.OPEN_MAIN_MENU_BOOK, mainMenu));

	}

	public static Menu getFirstMenu() {
		return mainMenu;
	}
}
