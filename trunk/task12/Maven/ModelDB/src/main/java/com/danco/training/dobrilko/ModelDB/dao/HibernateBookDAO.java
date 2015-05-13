package com.danco.training.dobrilko.ModelDB.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.danco.training.dobrilko.ModelDB.abstractdao.AbstractHibernateDAO;
import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;
import com.danco.training.dobrilko.PropertyController.property.PropertyStorage;

public class HibernateBookDAO extends AbstractHibernateDAO<Book, Integer> {

	public HibernateBookDAO() {
		super(Book.class);
	}

	@SuppressWarnings("unchecked")
	public List<Book> getUnclaimed(Session session) throws PersistException {

		Criteria criteria = session.createCriteria(Book.class);
		Date date = new Date();
		Calendar current = Calendar.getInstance();
		current.setTime(date);
		current.add(Calendar.MONTH, -PropertyStorage.getInstance()
				.getMonthsToMarkUnclaimed());
		criteria.add(Restrictions.lt("arrivalDate", current.getTime()));

		return criteria.list();

	}

}
