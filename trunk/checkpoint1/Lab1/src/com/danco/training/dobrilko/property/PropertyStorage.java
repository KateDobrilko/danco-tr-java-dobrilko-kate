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

	public String getReadPath() {
		String readPath = "";
		readPath = properties.getProperty("READ_PATH");
		return readPath;
	}

	public String getWritePath() {
		String writePath = "";
		writePath = properties.getProperty("WRITE_PATH");
		return writePath;
	}

}
