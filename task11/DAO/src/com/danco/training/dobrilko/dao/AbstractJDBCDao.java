package com.danco.training.dobrilko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.danco.training.dobrilko.daoentity.PKHolder;

public abstract class AbstractJDBCDao<T extends PKHolder<PK>, PK extends Integer>
		implements GenericDao<T, PK> {

	public abstract String getSelectQuery(String parameterName);

	public abstract String getCreateQuery();

	public abstract String getUpdateQuery();

	public abstract String getDeleteQuery();

	protected abstract List<T> parseResultSet(ResultSet rs)
			throws PersistException;

	protected abstract void prepareStatementForInsert(
			PreparedStatement statement, T object) throws PersistException;

	protected abstract void prepareStatementForUpdate(
			PreparedStatement statement, T object) throws PersistException;

	@Override
	public T persist(T object, Connection connection) throws PersistException {
		T persistInstance;

		String sql = getCreateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForInsert(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException(
						"On persist modify more then 1 record: " + count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}

		sql = getSelectQuery("id") + " WHERE id = last_insert_id();";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			List<T> list = parseResultSet(rs);
			if ((list == null) || (list.size() != 1)) {
				throw new PersistException(
						"Exception on findByPK new persist data.");
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
		String sql = getSelectQuery("id");
		sql += " WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, key);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		if (list == null || list.size() == 0) {
			throw new PersistException("Record with PK = " + key
					+ " not found.");
		}
		if (list.size() > 1) {
			throw new PersistException("Received more than one record.");
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
				throw new PersistException(
						"On update modify more then 1 record: " + count);
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
				throw new PersistException(
						"On delete modify more then 1 record: " + count);
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
		String sql = getSelectQuery(parameterName);
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}

	public AbstractJDBCDao() {

	}
}
