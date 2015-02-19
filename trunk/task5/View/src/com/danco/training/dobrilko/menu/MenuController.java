package com.danco.training.dobrilko.menu;

import com.danco.training.dobrilko.ioutil.IOUtil;

public class MenuController {

    public void run() {
	
	    Navigator navigator = new Navigator();
	    MenuCreator.createMenu();
	    navigator.setCurrentMenu(MenuCreator.getFirstMenu());
	    while (navigator.getCurrentMenu() != null) {
		navigator.print();
		navigator.navigate(IOUtil.readInt());
	    
	} 

	}

    
}