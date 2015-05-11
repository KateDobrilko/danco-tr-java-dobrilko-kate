package com.danco.training.dobrilko.ModelDB.abstractdao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.danco.training.dobrilko.ModelDB.genericdao.GenericDAO;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;
import com.danco.training.dobrilko.ModelDB.primarykey.PKHolder;

public abstract class AbstractHibernateDAO<T extends PKHolder<PK>, PK extends Integer>
		implements GenericDAO<T, PK> {

	private final Class<T> clazz;

	protected AbstractHibernateDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(Session session, T object) throws PersistException {
		return (T) session.save(object);
	}

	@Override
	public void delete(Session session, T object) throws PersistException {
		session.delete(object);

	}

	@SuppressWarnings("unchecked")
	@Override
	public T getByPK(Session session, Integer id) throws PersistException {
		return (T) session.get(clazz, id);
	}

	@Override
	public void saveOrUpdate(Session session, T object) throws PersistException {
		session.saveOrUpdate(object);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Session session, String sortingParameter)
			throws PersistException {

		final Criteria criteria = session.createCriteria(clazz);
		criteria.addOrder(Order.asc(sortingParameter));
		return criteria.list();
	}

}
