package com.danco.training.dobrilko.propertyholder;

import java.util.Properties;

import com.danco.training.dobrilko.propertyreader.PropertyReader;

public class PropertyStorage {
	private static final String URL = "URL";
	private static final String PASSWORD = "PASSWORD";
	private static final String USER = "USER";
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

}