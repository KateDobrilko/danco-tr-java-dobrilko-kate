package com.danco.training.dobrilko.bookshop.propertiesutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);

	public static int getMonthsToMarkUnclaimed() {
		int monthsToMarkUnclaimed = 0;
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			Properties property = new Properties();
			property.load(fis);
			monthsToMarkUnclaimed = Integer.parseInt(property.getProperty("MONTHS_TO_MARK_UNCLAIMED"));

		} catch (FileNotFoundException e) {

			logger.error("File not found!", e);
		} catch (IOException e) {

			logger.error("IO Exception has been detected!", e);
		}
		return monthsToMarkUnclaimed;
	}

	public static boolean getMarkRepliesAsExecuted() {
		boolean markRepliesAsExecuted = false;
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			Properties property = new Properties();
			property.load(fis);
			markRepliesAsExecuted = Boolean.parseBoolean(property.getProperty("MARK_REPLIES_AS_EXECUTED"));

		} catch (FileNotFoundException e) {

			logger.error("File not found!", e);
		} catch (IOException e) {

			logger.error("IO Exception has been detected!", e);
		}
		return markRepliesAsExecuted;
	}

}
