package com.danco.training.dobrilko.ModelDB.training;


import java.util.HashMap;
import java.util.Map;

import com.danco.training.dobrilko.ModelDB.dao.HibernateBookDAO;
import com.danco.training.dobrilko.ModelDB.dao.HibernateOrderDAO;
import com.danco.training.dobrilko.ModelDB.dao.HibernateReplyDAO;
import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.entity.Order;
import com.danco.training.dobrilko.ModelDB.entity.Reply;
import com.danco.training.dobrilko.ModelDB.genericdao.GenericDAO;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;

public class HibernateDAOFactory implements DAOFactory {
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
	public HibernateDAOFactory() {
		creators = new HashMap<Class, DaoCreator>();
		creators.put(Book.class, new DaoCreator() {
			@SuppressWarnings({ "unchecked" })
			@Override
			public GenericDAO create() {
				return new HibernateBookDAO();
			}
		});
		creators.put(Order.class, new DaoCreator() {
			@SuppressWarnings({ "unchecked" })
			@Override
			public GenericDAO create() {
				return new HibernateOrderDAO();
			}
		});
		creators.put(Reply.class, new DaoCreator() {
			@SuppressWarnings({ "unchecked" })
			@Override
			public GenericDAO create() {
				return new HibernateReplyDAO();
			}
		});
	}

}