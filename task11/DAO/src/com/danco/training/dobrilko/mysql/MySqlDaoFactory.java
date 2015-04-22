package com.danco.training.dobrilko.mysql;

import com.danco.training.dobrilko.dao.*;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Order;
import com.danco.training.dobrilko.entity.Reply;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {

    private String user = "root";//Логин пользователя
    private String password = "teRR_2i2_TAng_vXC";//Пароль пользователя
    private String url = "jdbc:mysql://localhost:3306/Bookshop";//URL адрес
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера
    @SuppressWarnings("rawtypes")
	private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    @SuppressWarnings("rawtypes")
	public MySqlDaoFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Book.class, new DaoCreator<Connection>() {
            @SuppressWarnings("unchecked")
			@Override
            public GenericDao create(Connection connection) {
                return new MySqlBookDao(connection);
            }
        });
        creators.put(Order.class, new DaoCreator<Connection>() {
            @SuppressWarnings("unchecked")
			@Override
			public GenericDao create(Connection connection) {
                return new MySqlOrderDao(connection);
            }
        });
        creators.put(Reply.class, new DaoCreator<Connection>() {
            @SuppressWarnings("unchecked")
			@Override
            public GenericDao create(Connection connection) {
                return new MySqlReplyDao(connection);
            }
        });
    }
}

