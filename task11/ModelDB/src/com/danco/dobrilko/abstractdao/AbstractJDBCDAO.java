package com.danco.dobrilko.abstractdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.danco.dobrilko.genericdao.GenericDAO;
import com.danco.dobrilko.persistexception.PersistException;
import com.danco.dobrilko.primarykey.PKHolder;

public abstract class AbstractJDBCDAO<T extends PKHolder<PK>, PK extends Integer>
		implements GenericDAO<T, PK> {

	private static final String ON_DELETE_MODIFY_MORE_THEN_1_RECORD = "On delete modify more then 1 record: ";
	private static final String ON_UPDATE_MODIFY_MORE_THEN_1_RECORD = "On update modify more then 1 record: ";
	private static final String ON_PERSIST_MODIFY_MORE_THEN_1_RECORD = "On persist modify more then 1 record: ";
	private static final String WHERE_ID_LAST_INSERT_ID = " WHERE id = last_insert_id();";
	private static final String EXCEPTION_ON_FIND_BY_PK_NEW_PERSIST_DATA = "Exception on findByPK new persist data.";
	private static final String NOT_FOUND = " not found.";
	private static final String RECORD_WITH_PK = "Record with PK = ";
	private static final String RECEIVED_MORE_THAN_ONE_RECORD = "Received more than one record.";

	public abstract String getSelectQuery();

	public abstract String getSelectByPK();

	public abstract String getCreateQuery();

	public abstract String getUpdateQuery();

	public abstract String getDeleteQuery();

	protected abstract List<T> parseResultSet(ResultSet rs)
			throws PersistException;

	protected abstract void prepareStatementForInsert(
			PreparedStatement statement, T object) throws PersistException;

	protected abstract void prepareStatementForUpdate(
			PreparedStatement statement, T object) throws PersistException;

	protected abstract void prepareStatementForReading(
			PreparedStatement statement, String attributeToSortBy)
			throws PersistException;

	protected abstract void prepareStatementForDelete(
			PreparedStatement statement, Integer id) throws PersistException;

	@Override
	public T persist(T object, Connection connection) throws PersistException {
		T persistInstance;

		String sql = getCreateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForInsert(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException(ON_PERSIST_MODIFY_MORE_THEN_1_RECORD
						+ count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}

		sql = getSelectQuery() + WHERE_ID_LAST_INSERT_ID;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			List<T> list = parseResultSet(rs);
			if ((list == null) || (list.size() != 1)) {
				throw new PersistException(
						EXCEPTION_ON_FIND_BY_PK_NEW_PERSIST_DATA);
			}
			persistInstance = list.iterator().next();
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return persistInstance;
	}

	@Override
	public T getByPK(Integer key, Connection connection)
			throws PersistException {
		List<T> list;
		String sql = getSelectByPK();

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, key);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		if (list == null || list.size() == 0) {
			throw new PersistException(RECORD_WITH_PK + key + NOT_FOUND);
		}
		if (list.size() > 1) {
			throw new PersistException(RECEIVED_MORE_THAN_ONE_RECORD);
		}
		return list.iterator().next();
	}

	@Override
	public void update(T object, Connection connection) throws PersistException {
		String sql = getUpdateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			prepareStatementForUpdate(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException(ON_UPDATE_MODIFY_MORE_THEN_1_RECORD
						+ count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public void delete(T object, Connection connection) throws PersistException {
		String sql = getDeleteQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			try {
				statement.setObject(1, object.getId());
			} catch (Exception e) {
				throw new PersistException(e);
			}
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException(ON_DELETE_MODIFY_MORE_THEN_1_RECORD
						+ count);
			}
			statement.close();
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public List<T> getAll(String parameterName, Connection connection)
			throws PersistException {
		List<T> list;
		String sql = getSelectQuery();

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForReading(statement, parameterName);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

	public AbstractJDBCDAO() {

	}
}
