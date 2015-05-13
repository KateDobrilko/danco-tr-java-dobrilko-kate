package com.danco.training.dobrilko.PropertyController.property;

import java.util.Properties;

public class PropertyStorage {
	private static final String BOOKSHOP_CONTROLLER = "BOOKSHOP_CONTROLLER";
	private static final String URL = "URL";
	private static final String PASSWORD = "PASSWORD";
	private static final String USER = "USER";
	private static final String MARK_REPLIES_AS_EXECUTED = "MARK_REPLIES_AS_EXECUTED";
	private static final String MONTHS_TO_MARK_UNCLAIMED = "MONTHS_TO_MARK_UNCLAIMED";
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

	public String getUrl() {
		return properties.getProperty(URL);
	}

	public String getPassWord() {
		return properties.getProperty(PASSWORD);
	}

	public String getUser() {
		return properties.getProperty(USER);
	}
	
	public int getMonthsToMarkUnclaimed() {
		int monthsToMarkUnclaimed = 0;
		monthsToMarkUnclaimed = Integer.parseInt(properties
				.getProperty(MONTHS_TO_MARK_UNCLAIMED));
		return monthsToMarkUnclaimed;
	}

	public boolean getMarkRepliesAsExecuted() {
		boolean markRepliesAsExecuted = false;
		markRepliesAsExecuted = Boolean.parseBoolean(properties
				.getProperty(MARK_REPLIES_AS_EXECUTED));
		return markRepliesAsExecuted;
	}
	
	public String getBookshopControllerName() {

		return properties.getProperty(BOOKSHOP_CONTROLLER);
	}

}
