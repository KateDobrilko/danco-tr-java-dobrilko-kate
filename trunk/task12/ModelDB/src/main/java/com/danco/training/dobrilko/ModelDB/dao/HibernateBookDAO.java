package com.danco.training.dobrilko.ModelDB.dao;

import com.danco.training.dobrilko.ModelDB.abstractdao.AbstractHibernateDAO;
import com.danco.training.dobrilko.ModelDB.entity.Book;

public class HibernateBookDAO extends AbstractHibernateDAO<Book, Integer> {

	public HibernateBookDAO() {
		super(Book.class);
	}

}
