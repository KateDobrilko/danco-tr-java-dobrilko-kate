package com.danco.training.dobrilko.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	public final static String CONFIG_PATH = "src/config.properties";

	public static void setProperties(Properties p) {
		try {
			FileInputStream fis = new FileInputStream(CONFIG_PATH);
			p.load(fis);

		} catch (FileNotFoundException e) {
			System.out
					.println("FileNotFoundException has been found in PropertyReader!");

		} catch (IOException e) {
			System.out
			.println("IOException has been found in PropertyReader!");
		}

	}
}
