package com.danco.training.dobrilko.propertyreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyReader {
	private static final String IO_EXCEPTION_HAS_BEEN_DETECTED = "IO Exception has been detected!";

	private static final String FILE_NOT_FOUND = "File not found!";

	public final static String CONFIG_PATH = "src/com/danco/training/dobrilko/txt/config.properties";

	private static Logger logger = Logger.getLogger(PropertyReader.class);

	public static void setProperties(Properties p) {
		try {
			FileInputStream fis = new FileInputStream(CONFIG_PATH);
			p.load(fis);

		} catch (FileNotFoundException e) {

			logger.error(FILE_NOT_FOUND, e);
		} catch (IOException e) {

			logger.error(IO_EXCEPTION_HAS_BEEN_DETECTED, e);
		}

	}
}
