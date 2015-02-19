package com.danco.training.dobrilko.menu;

import com.danco.training.dobrilko.ioutil.IOUtil;

public class Navigator {

    private Menu currentMenu = new Menu();

    public Menu getCurrentMenu() {
	return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
	this.currentMenu = currentMenu;
    }

    public void navigate(int index) {
	if ((index < currentMenu.getMenuItems().size()) && (index >= 0)) {
	    this.currentMenu.getItem(index).doAction();
	    setCurrentMenu(this.currentMenu.getItem(index).getMenu());
	} else {
	    setCurrentMenu(null);
	}
    }

    public void print() {
	StringBuilder sb = new StringBuilder();
	for (MenuItem mi : currentMenu.getMenuItems()) {
	    sb.append(mi.getTitle() + System.lineSeparator());
	}
	
	IOUtil.print(sb.toString());
    }

}
