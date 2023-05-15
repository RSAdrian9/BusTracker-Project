package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Connections.ConnectionData;
import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.Domain.Admin;

import java.sql.SQLException;
import java.util.List;

public class AdminDAO implements DAO<Admin>{

    private final static String FINDALL = "SELECT * FROM admin";
    private final static String FINDBYID = "SELECT * FROM admin WHERE id = ?";
    private final static String INSERT = "INSERT INTO admin (line_name, place, route, timetable) VALUES (?, ?, ?, ?)";
    private final static String DELETE = "DELETE FROM line WHERE id = ?";
    private final static String UPDATE = "UPDATE line SET line_name = ?, place = ?, route = ?, timetable = ? WHERE id = ?";

    private ConnectionData conn;

    public AdminDAO(ConnectionData conn){

        this.conn = conn;
    }
    public AdminDAO(){

        this.conn = ConnectionMySQL.getConnect();
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        return null;
    }

    @Override
    public Admin findById(String id) throws SQLException {
        return null;
    }

    @Override
    public Admin insert(Admin entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Admin entity) throws SQLException {

    }

    @Override
    public Admin update(Admin entity) throws SQLException {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
