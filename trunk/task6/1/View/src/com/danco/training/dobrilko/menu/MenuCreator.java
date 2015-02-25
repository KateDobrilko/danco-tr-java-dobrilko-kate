package com.danco.training.dobrilko.menu;

import com.danco.training.dobrilko.action.orderingsystem.*;
import com.danco.training.dobrilko.action.readwrite.ReadFromFileAction;
import com.danco.training.dobrilko.action.readwrite.WriteToFileAction;
import com.danco.training.dobrilko.action.replysystem.*;
import com.danco.training.dobrilko.action.store.*;

public class MenuCreator {
	private static Menu mainMenu = new Menu();
	private static Menu readAndWriteMenu = new Menu();
	private static Menu orderingSystemMenu = new Menu();
	private static Menu replySystemMenu = new Menu();
	private static Menu storeMenu = new Menu();

	public static void createMenu() {

		readAndWriteMenu.addMenuItem(new MenuItem(new ReadFromFileAction(), "Press 0 - Read from file", mainMenu));
		readAndWriteMenu.addMenuItem(new MenuItem(new WriteToFileAction(), "Press 1 - Write to file", mainMenu));
		readAndWriteMenu.addMenuItem(new MenuItem(null, "Press 2- Exit", null));

		mainMenu.addMenuItem(new MenuItem(null, "Press 0 - Open Ordering System Options", orderingSystemMenu));
		mainMenu.addMenuItem(new MenuItem(null, "Press 1 - Open Reply System Options", replySystemMenu));
		mainMenu.addMenuItem(new MenuItem(null, "Press 2 - Open Store System Options", storeMenu));
		mainMenu.addMenuItem(new MenuItem(null, "Press 3 - Open Read & Write Options", readAndWriteMenu));
		mainMenu.addMenuItem(new MenuItem(null, "Press 4 - Exit", null));

		orderingSystemMenu.addMenuItem(new MenuItem(new AddOrderAction(), "Press 0  - Add Order", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new CancelOrderAction(), "Press 1  - Cancel Order", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new ExecuteOrderAction(), "Press 2  - Execute Order", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new ShowAllOrdersAction(), "Press 3  - Show All Orders", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new ShowNumberOfExecutedOrdersAction(), "Press 4  - Show Number Of Executed Orders", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new ShowSumOfExecutedOrdersAction(), "Press 5  - Show Sum Of Executed Orders", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new SortByDateAction(), "Press 6  - Sort  Orders By Date", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new SortByExecutionAction(), "Press 7  - Sort  Orders By Execution", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(new SortByPriceAction(), "Press 8  - Sort  Orders By Price", orderingSystemMenu));
		orderingSystemMenu.addMenuItem(new MenuItem(null, "Press 9 - Open Main Menu", mainMenu));

		replySystemMenu.addMenuItem(new MenuItem(new AddReplyAction(), "Press 0 - Add Reply", replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(new ExecuteReplyAction(), "Press 1 - Execute Reply", replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(new ShowAllReplies(), "Press 2 - Show Replies", replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(new SortByAlphabetAction(), "Press 3  - Sort  Replies By Alphabet", replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(new SortByNumberAction(), "Press 4  - Sort  Replies By Number", replySystemMenu));
		replySystemMenu.addMenuItem(new MenuItem(null, "Press 5 - Open Main Menu", mainMenu));

		storeMenu.addMenuItem(new MenuItem(new AddBookAction(), "Press 0 - Add Book", storeMenu));
		storeMenu.addMenuItem(new MenuItem(new DeleteBookAction(), "Press 1 - Delete Book", storeMenu));
		storeMenu.addMenuItem(new MenuItem(new ShowAllBooksAction(), "Press 2 - Show All Books", storeMenu));
		storeMenu.addMenuItem(new MenuItem(new ShowUnclaimedBooksAction(), "Press 3 - Show Unclaimed Books", storeMenu));
		storeMenu.addMenuItem(new MenuItem(new SortBooksByPriceAction(), "Press 4 - Sort Books By Price", storeMenu));
		storeMenu.addMenuItem(new MenuItem(new SortByNameAction(), "Press 5 - Sort Books By Name", storeMenu));
		storeMenu.addMenuItem(new MenuItem(new SortByPublicationDateAction(), "Press 6 - Sort Books By Publication Date", storeMenu));
		storeMenu.addMenuItem(new MenuItem(null, "Press 7 - Open Main Menu", mainMenu));

	}

	public static Menu getFirstMenu() {
		return readAndWriteMenu;
	}
}
