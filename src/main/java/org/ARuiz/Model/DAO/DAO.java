package org.ARuiz.Model.DAO;

import java.sql.SQLException;
import java.util.List;
public interface DAO<T> extends AutoCloseable {
    List<T> findAll() throws SQLException;
    T findById(String id) throws SQLException;
    T insert(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    T update(T entity) throws SQLException;

}
