package com.danco.training.dobrilko.test;

import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.menu.MenuController;
import com.danco.training.dobrilko.property.PropertyStorage;

public class Test {
	public static void main(String[] args) {
		BookshopController.readFromFile(PropertyStorage.getInstance().getSerializationStoragePath());
		BookshopController.writeOrdersWithReflection();
		MenuController menuController = new MenuController();
		menuController.run();
	}
}
