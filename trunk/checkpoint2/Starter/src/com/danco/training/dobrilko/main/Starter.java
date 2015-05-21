package com.danco.training.dobrilko.main;

import com.danco.training.dobrilko.controller.Controller;

public class Starter {

	private static final String PATH = "src/com/danco/training/dobrilko/txt/info.csv";

	public static void main(String[] args) {

		Controller controller = new Controller();
		controller.saveOrUpdateFromCSV(PATH);
	}
}
