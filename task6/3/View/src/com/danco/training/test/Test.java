package com.danco.training.test;

import com.danco.training.dobrilko.controller.BookShopController;
import com.danco.training.dobrilko.menu.MenuController;

public class Test {
	public static void main(String[] args) {
		BookShopController.initialize();
		MenuController menuController = new MenuController();
		menuController.run();
	}
}
