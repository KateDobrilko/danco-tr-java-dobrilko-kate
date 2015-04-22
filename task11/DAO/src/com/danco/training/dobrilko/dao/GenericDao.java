package com.danco.training.dobrilko.dao;

import java.io.Serializable;
import java.util.List;

import com.danco.training.dobrilko.daoentity.Identified;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    
    public T create() throws PersistException;

    
    public T persist(T object)  throws PersistException;

    public T getByPK(PK key) throws PersistException;

    public void update(T object) throws PersistException;

    public void delete(T object) throws PersistException;

    public List<T> getAll() throws PersistException;
}

