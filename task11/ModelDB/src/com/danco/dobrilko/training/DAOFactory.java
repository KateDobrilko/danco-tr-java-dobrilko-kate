package com.danco.dobrilko.training;

import com.danco.dobrilko.genericdao.GenericDAO;
import com.danco.dobrilko.persistexception.PersistException;

public interface DAOFactory<Context> {

	public interface DaoCreator {
		public GenericDAO<?, ?> create();
	}

	public GenericDAO<?, ?> getDao(Class<?> dtoClass) throws PersistException;
}