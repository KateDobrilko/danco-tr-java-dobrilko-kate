package com.danco.training.dobrilko.ModelDB.genericdao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;
import com.danco.training.dobrilko.ModelDB.primarykey.PKHolder;

public interface GenericDAO<T extends PKHolder<PK>, PK extends Serializable> {

	T save(Session session, T object) throws PersistException;

	void delete(Session session, T object) throws PersistException;

	T getByPK(Session session, Integer id) throws PersistException;

	void saveOrUpdate(Session session, T object) throws PersistException;

	List<T> getAll(Session session, String sortingParameter)
			throws PersistException;

}
