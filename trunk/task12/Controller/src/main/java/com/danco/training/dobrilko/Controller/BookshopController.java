package com.danco.training.dobrilko.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import com.danco.training.dobrilko.API.IBookshopController;
import com.danco.training.dobrilko.Controller.parser.ParseUtil;
import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.entity.Order;
import com.danco.training.dobrilko.ModelDB.entity.Reply;
import com.danco.training.dobrilko.ModelDB.genericdao.GenericDAO;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;
import com.danco.training.dobrilko.ModelDB.training.DAOFactory;
import com.danco.training.dobrilko.ModelDB.training.HibernateDAOFactory;
import com.danco.training.dobrilko.hibernateutil.HibernateUtil;

public class BookshopController implements IBookshopController {

	private Logger logger = Logger.getLogger(BookshopController.class);

	@SuppressWarnings("unchecked")
	@Override
	public double getSumOfExecutedOrders(Date startDate, Date endDate) {
		return 0;
	//TODO
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addOrder(String orderString) {
		HibernateUtil.buildSessionFactory();
		DAOFactory factory = new HibernateDAOFactory();
		GenericDAO<Order, Integer> dao;
		
		try {
			dao = (GenericDAO<Order, Integer>) factory.getDao(Order.class);
			HibernateUtil.openSessionAndBindToThread();

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.beginTransaction();
			dao.saveOrUpdate(session, ParseUtil.parseOrderString(orderString));
			session.getTransaction().commit();
		} catch (NumberFormatException | PersistException e) {
			logger.error(e);
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();

	}

	@Override
	public void cancelOrder(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeOrder(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOrdersString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExecutedOrdersString(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfExecutedOrders(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sortOrdersByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sortOrdersByExecution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sortOrdersByPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markUnclaimedBooks(Connection connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOrderStringById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addBook(String bookString) {
		HibernateUtil.buildSessionFactory();
		DAOFactory factory = new HibernateDAOFactory();
		GenericDAO<Book, Integer> dao;
		
		try {
			dao = (GenericDAO<Book, Integer>) factory.getDao(Book.class);
			HibernateUtil.openSessionAndBindToThread();

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.beginTransaction();
			dao.saveOrUpdate(session, ParseUtil.parseBookString(bookString));
			session.getTransaction().commit();
		} catch (NumberFormatException | PersistException e) {
			logger.error(e);
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();

	}

	@Override
	public void deleteBook(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUnclaimedBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sortBookByPublicationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sortBookByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sortBookByPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBookById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReplies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sortRepliesByAlphabet() {
		// TODO Auto-generated method stub

	}

	@Override
	public String sortRepliesByNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addReply(String replyString) {
		HibernateUtil.buildSessionFactory();
		DAOFactory factory = new HibernateDAOFactory();
		GenericDAO<Reply, Integer> dao;
		
		try {
			dao = (GenericDAO<Reply, Integer>) factory.getDao(Reply.class);
			HibernateUtil.openSessionAndBindToThread();

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.beginTransaction();
			dao.saveOrUpdate(session, ParseUtil.parseReplyString(replyString));
			session.getTransaction().commit();
		} catch (NumberFormatException | PersistException e) {
			logger.error(e);
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();
	}

	@Override
	public void markRepliesAsExecuted(Connection connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getReplyById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
