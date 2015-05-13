package com.danco.training.dobrilko.ModelDB.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.danco.training.dobrilko.ModelDB.abstractdao.AbstractHibernateDAO;
import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.entity.Order;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;

public class HibernateOrderDAO extends AbstractHibernateDAO<Order, Integer> {

	public HibernateOrderDAO() {
		super(Order.class);
	}

	public List<Order> getOrderByBook(Session session, Book book) {

		Criteria criteria = session.createCriteria(Order.class);

		@SuppressWarnings("unchecked")
		List<Order> orders = criteria.createAlias("book", "order_to_book")
				.add(Restrictions.eq("order_to_book.idB", book.getId())).list();
		return orders;
	}

	public void deleteOrderByBookRelation(Session session, Book book)
			throws PersistException {
		for (Order order : getOrderByBook(session, book)) {

			delete(session, order);

		}
	}

	@SuppressWarnings("unchecked")
	public List<Order> getExecuted(Session session) {

		Criteria criteria = session.createCriteria(Order.class);
		List<Order> orders = criteria.add(Restrictions.eq("executed", true))
				.list();
		return orders;
	}

	public void executeOrder(Session session, Integer id)
			throws PersistException {

		Order order = getByPK(session, id);
		order.setExecuted(false);
		saveOrUpdate(session, order);

	}

	public List<Book> getBooksByOrderId(Session session, Integer id)
			throws PersistException {

		Order order = getByPK(session, id);
		return order.getBooks();

	}

}
