package com.danco.dobrilko.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.danco.dobrilko.property.PropertyStorage;

public class ConnectionManager {
	private static Logger logger = Logger.getLogger(ConnectionManager.class);

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			logger.error(e);
			return null;
		}

		Connection connection = null;

		try {
			String url = PropertyStorage.getInstance().getUrl();
			String password = PropertyStorage.getInstance().getPassWord();
			String user = PropertyStorage.getInstance().getUser();
			connection = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			logger.error(e);
			return null;
		}

		if (connection != null) {
			return connection;
		} else {
			return null;
		}
	}
}
