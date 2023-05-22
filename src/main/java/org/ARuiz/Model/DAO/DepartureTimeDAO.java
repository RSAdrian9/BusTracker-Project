package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Domain.DepartureTime;
import org.ARuiz.Model.Domain.StopAdmin;

import java.sql.SQLException;
import java.util.List;

public class DepartureTimeDAO implements DAO<DepartureTime>{
    @Override
    public List<DepartureTime> findAll() throws SQLException {
        return null;
    }

    @Override
    public DepartureTime findById(int id) throws SQLException {
        return null;
    }

    @Override
    public DepartureTime insert(DepartureTime entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(DepartureTime entity) throws SQLException {

    }

    @Override
    public DepartureTime update(DepartureTime entity) throws SQLException {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
