package com.danco.training.dobrilko.ModelDB.training;

import com.danco.training.dobrilko.ModelDB.genericdao.GenericDAO;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;

public interface DAOFactory {

	public interface DaoCreator {
		public GenericDAO<?, ?> create();
	}

	public GenericDAO<?, ?> getDao(Class<?> dtoClass) throws PersistException;
}