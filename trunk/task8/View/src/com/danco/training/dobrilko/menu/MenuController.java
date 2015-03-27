package com.danco.training.dobrilko.menu;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.danco.training.dobrilko.ioutil.IOUtil;

public class MenuController extends Thread {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean exitFlag = false;

	public MenuController(ObjectOutputStream out, ObjectInputStream in) {
		this.in = in;
		this.out = out;
	}

	public void run() {

		Navigator navigator = new Navigator(in, out);
		MenuCreator.createMenu();
		navigator.setCurrentMenu(MenuCreator.getFirstMenu());
		navigator.print();
		this.exitFlag = !navigator.navigate(IOUtil.readInt());

	}

	public boolean getExitFlag() {
		return exitFlag;
	}

}
