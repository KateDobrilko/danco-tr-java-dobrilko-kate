package com.danco.training.dobrilko.reflectionproperty;

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
