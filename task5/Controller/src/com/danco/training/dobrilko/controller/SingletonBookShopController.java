package com.danco.training.dobrilko.controller;

public class SingletonBookShopController {
    private static BookShopController instance = new BookShopController();

    public static BookShopController getInstance() {
	return instance;
    }

}
