package com.danco.training.dobrilko.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.controller.constant.ConstantStorage;

public class PropertyReader {

	private static Logger logger = Logger.getLogger(PropertyReader.class);

	public static void setProperties(Properties p) {
		try {
			FileInputStream fis = new FileInputStream(
					ConstantStorage.CONFIG_PATH);
			p.load(fis);

		} catch (FileNotFoundException e) {

			logger.error("File not found!", e);
		} catch (IOException e) {

			logger.error("IO Exception has been detected!", e);
		}

	}
}
