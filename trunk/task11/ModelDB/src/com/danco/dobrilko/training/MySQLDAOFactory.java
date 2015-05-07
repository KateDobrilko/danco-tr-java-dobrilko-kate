package com.danco.dobrilko.training;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.danco.dobrilko.dao.MySqlBookDAO;
import com.danco.dobrilko.dao.MySqlOrderDAO;
import com.danco.dobrilko.dao.MySqlReplyDAO;
import com.danco.dobrilko.entity.Book;
import com.danco.dobrilko.entity.Order;
import com.danco.dobrilko.entity.Reply;
import com.danco.dobrilko.genericdao.GenericDAO;
import com.danco.dobrilko.persistexception.PersistException;

public class MySQLDAOFactory implements DAOFactory<Connection> {
	private static final String NOT_FOUND = " not found.";
	private static final String DAO_OBJECT_FOR = "Dao object for ";
	@SuppressWarnings("rawtypes")
	private Map<Class, DaoCreator> creators;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public GenericDAO getDao(Class dtoClass) throws PersistException {

		DaoCreator creator = creators.get(dtoClass);
		if (creator == null) {
			throw new PersistException(DAO_OBJECT_FOR + dtoClass
					+ NOT_FOUND);
		}
		return creator.create();
	}

	@SuppressWarnings("rawtypes")
	public MySQLDAOFactory() {
		creators = new HashMap<Class, DaoCreator>();
		creators.put(Book.class, new DaoCreator() {
			@SuppressWarnings({ "unchecked" })
			@Override
			public GenericDAO create() {
				return new MySqlBookDAO();
			}
		});
		creators.put(Order.class, new DaoCreator() {
			@SuppressWarnings({ "unchecked" })
			@Override
			public GenericDAO create() {
				return new MySqlOrderDAO();
			}
		});
		creators.put(Reply.class, new DaoCreator() {
			@SuppressWarnings({ "unchecked" })
			@Override
			public GenericDAO create() {
				return new MySqlReplyDAO();
			}
		});
	}

}