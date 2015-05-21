package com.danco.training.dobrilko.genericdao;

import java.io.Serializable;
import java.sql.Connection;


import java.util.List;

import com.danco.training.dobrilko.persistexception.PersistException;
import com.danco.training.dobrilko.primarykey.PKHolder;

public interface GenericDAO<T extends PKHolder<PK>, PK extends Serializable> {

	public void save(T object, Connection connection)
			throws PersistException;

	public void update(T object, Connection connection) throws PersistException;
	
	
	public List<T> getAll(Connection connection)
			throws PersistException;

}
