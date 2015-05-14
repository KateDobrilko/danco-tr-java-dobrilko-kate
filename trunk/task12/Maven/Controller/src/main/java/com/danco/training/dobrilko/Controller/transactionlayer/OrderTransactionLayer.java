package com.danco.training.dobrilko.Controller.transactionlayer;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.danco.training.dobrilko.Controller.BookshopController;
import com.danco.training.dobrilko.Controller.parser.ParseUtil;
import com.danco.training.dobrilko.ModelDB.dao.HibernateOrderDAO;
import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.entity.Order;
import com.danco.training.dobrilko.ModelDB.genericdao.GenericDAO;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;
import com.danco.training.dobrilko.ModelDB.training.DAOFactory;
import com.danco.training.dobrilko.ModelDB.training.HibernateDAOFactory;
import com.danco.training.dobrilko.hibernateutil.HibernateUtil;

public class OrderTransactionLayer {
	private DAOFactory factory = new HibernateDAOFactory();
	private GenericDAO<Order, Integer> daoOrder;
	private Logger logger = Logger.getLogger(BookshopController.class);

	@SuppressWarnings("unchecked")
	public OrderTransactionLayer() {
		try {
			daoOrder = (GenericDAO<Order, Integer>) factory.getDao(Order.class);
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public double getSumOfExecutedOrders() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();
		double globalSum = 0;
		Transaction tr = session.beginTransaction();
		for (Order order : ((HibernateOrderDAO) daoOrder).getExecuted(session)) {
			double sum = 0;
			for (Book book : ((HibernateOrderDAO) daoOrder).getBooksByOrderId(
					session, order.getId())) {
				sum += book.getPrice();
				globalSum += book.getPrice();
			}
			Order ord = daoOrder.getByPK(session, order.getId());
			ord.setSum(sum);
			sum = 0;
			daoOrder.saveOrUpdate(session, ord);
		}

		tr.commit();
		return globalSum;
	}

	public void addOrder(String orderString) throws PersistException {

		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		session.beginTransaction();
		daoOrder.saveOrUpdate(session, ParseUtil.parseOrderString(orderString));
		session.getTransaction().commit();

	}

	public void cancelOrder(Integer id) throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.openSession();

		Transaction tr = session.beginTransaction();
		for (Book book : ((HibernateOrderDAO) daoOrder).getBooksByOrderId(
				session, id)) {

			book.setNumberOfExemplars(book.getNumberOfExemplars() + 1);

		}
		daoOrder.delete(session, daoOrder.getByPK(session, id));
		tr.commit();
		session.close();
	}

	public void executeOrder(Integer id) throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();
		Transaction tr = session.beginTransaction();
		for (Book book : ((HibernateOrderDAO) daoOrder).getBooksByOrderId(
				session, id)) {
			if (book.getNumberOfExemplars() != 0) {

				book.setNumberOfExemplars(book.getNumberOfExemplars() - 1);
			}
		}

		Order order = daoOrder.getByPK(session, id);
		order.setExecuted(true);
		daoOrder.saveOrUpdate(session, order);
		tr.commit();

	}

	public String getOrdersString() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		StringBuilder sb = new StringBuilder();
		for (Order order : daoOrder.getAll(session, "id")) {
			sb.append(ParseUtil.orderToString(order));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public String getExecutedOrdersString() {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		StringBuilder sb = new StringBuilder();
		for (Order order : ((HibernateOrderDAO) daoOrder).getExecuted(session)) {
			sb.append(ParseUtil.orderToString(order));
			sb.append(System.lineSeparator());
		}

		return sb.toString();

	}

	public Integer getNumberOfExecutedOrders() {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		return ((HibernateOrderDAO) daoOrder).getExecuted(session).size();
	}

	public String sortOrdersByDate() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		StringBuilder sb = new StringBuilder();
		for (Order order : daoOrder.getAll(session, "executionDate")) {
			sb.append(ParseUtil.orderToString(order));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public String sortOrdersByExecution() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		StringBuilder sb = new StringBuilder();
		for (Order order : daoOrder.getAll(session, "executed")) {
			sb.append(ParseUtil.orderToString(order));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

}
