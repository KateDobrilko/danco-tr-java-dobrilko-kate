package com.danco.training.dobrilko.property;

import java.util.Properties;

public class PropertyStorage {
    
	private static Properties properties = new Properties();
	private static PropertyStorage instance;
	private PropertyStorage() {
		PropertyReader.setProperties(properties);
	}
	

	public static PropertyStorage getInstance() {
		if (instance == null) {
			instance = new PropertyStorage();
		}

		return instance;

	}
	public Properties getProperties() {
		return properties;
	}

	public int getMonthsToMarkUnclaimed() {
		int monthsToMarkUnclaimed = 0;
		monthsToMarkUnclaimed = Integer.parseInt(properties
				.getProperty("MONTHS_TO_MARK_UNCLAIMED"));
		return monthsToMarkUnclaimed;
	}

	public boolean getMarkRepliesAsExecuted() {
		boolean markRepliesAsExecuted = false;
		markRepliesAsExecuted = Boolean.parseBoolean(properties
				.getProperty("MARK_REPLIES_AS_EXECUTED"));
		return markRepliesAsExecuted;
	}

	public String getCSVBookFilePath() {
		return properties.getProperty("CSV_BOOK_FILE_PATH");
	}

	public String getCSVOrderFilePath() {
		return properties.getProperty("CSV_ORDER_FILE_PATH");
	}

	public String getCSVReplyFilePath() {
		return properties.getProperty("CSV_REPLY_FILE_PATH");
	}

	public String getSerializationStoragePath() {
		return properties.getProperty("SERIALIZATION_STORAGE_PATH");
	}
	
	public String getCSVReplyReflectionFilePath() {
		return properties.getProperty("CSV_REPLY_REFLECTION_FILE_PATH");
	}
	
	public String getCSVOrderReflectionFilePath() {
		return properties.getProperty("CSV_ORDER_REFLECTION_FILE_PATH");
	}
	
	public String getCSVBookReflectionFilePath() {
		return properties.getProperty("CSV_BOOK_REFLECTION_FILE_PATH");
	}

}
