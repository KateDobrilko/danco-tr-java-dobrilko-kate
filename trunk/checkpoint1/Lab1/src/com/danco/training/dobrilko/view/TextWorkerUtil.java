package com.danco.training.dobrilko.view;

import java.util.ArrayList;

public class TextWorkerUtil {

	public final static String DELIMETER = System.lineSeparator();

	public static ArrayList<Integer> parse(String input) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		String[] stringArray = input.split(DELIMETER);
		for (String str : stringArray) {
			if (!str.equals("")) {
				try {
					array.add(Integer.parseInt(str));
				} catch (NumberFormatException e) {
					System.out
							.println("Error! Number formal exception found. Please, check your input.");
				}
			}

		}

		return array;

	}
}
