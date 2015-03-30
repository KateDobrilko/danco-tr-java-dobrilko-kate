package com.danco.training.dobrilko.menu;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.danco.training.dobrilko.ioutil.IOUtil;

public class MenuController {

	private Navigator navigator;
	private boolean exitFlag = false;

	public MenuController() {
		navigator = new Navigator();
		MenuCreator.createMenu();
		navigator.setCurrentMenu(MenuCreator.getFirstMenu());
	}

	public void run(ObjectOutputStream out, ObjectInputStream in) {

		navigator.print();
		this.exitFlag = !navigator.navigate(IOUtil.readInt(), in, out);

	}

	public boolean getExitFlag() {
		return exitFlag;
	}

}
