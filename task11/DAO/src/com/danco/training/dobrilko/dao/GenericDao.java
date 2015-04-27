package com.danco.training.dobrilko.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import com.danco.training.dobrilko.daoentity.PKHolder;


public interface GenericDao<T extends PKHolder<PK>, PK extends Serializable> {

    
    public T create(Connection connection) throws PersistException;

    
    public T persist(T object, Connection connection)  throws PersistException;

    public T getByPK(PK key, Connection connection) throws PersistException;

    public void update(T object, Connection connection) throws PersistException;

    public void delete(T object, Connection connection) throws PersistException;

    public List<T> getAll(String parameterName, Connection connection) throws PersistException;
}

