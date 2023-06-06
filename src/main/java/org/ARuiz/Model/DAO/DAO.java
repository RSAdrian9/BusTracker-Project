package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Domain.Stop;

import java.sql.SQLException;
import java.util.List;
public interface DAO<T> extends AutoCloseable {
    List<T> findAll() throws SQLException;
    T findById(int id) throws SQLException;
    T insert(T entity) throws SQLException;
    Stop delete(T entity) throws SQLException;
    T update(T entity) throws SQLException;

}
