package com.danco.training.dobrilko.controller.property;

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

	

}
