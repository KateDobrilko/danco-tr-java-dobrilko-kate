package com.danco.training.dobrilko.menu;

import java.util.ArrayList;

public class Menu {
    
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Menu(ArrayList<MenuItem> menuItems) {
	this.menuItems = menuItems;
    }

    public Menu() {

    }

    public ArrayList<MenuItem> getMenuItems() {
	return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
	this.menuItems = menuItems;
    }

    public void addMenuItem(MenuItem mi) {
	this.menuItems.add(mi);
    }

    public MenuItem getItem(int menuNumber) {
	if ((menuNumber < this.menuItems.size()) && (menuNumber >= 0)) {
	    return this.menuItems.get(menuNumber);
	} else {
	    return null;
	}
    }

}
