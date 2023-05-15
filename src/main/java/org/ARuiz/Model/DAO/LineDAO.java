package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Connections.ConnectionData;
import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.Domain.Line;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineDAO implements DAO<Line>{
    private final static String FINDALL = "SELECT * FROM line";
    private final static String FINDBYID = "SELECT * FROM line WHERE id = ?";
    private final static String INSERT = "INSERT INTO line (line_name, place, route, timetable) VALUES (?, ?, ?, ?)";
    private final static String DELETE = "DELETE FROM line WHERE id = ?";
    private final static String UPDATE = "UPDATE line SET line_name = ?, place = ?, route = ?, timetable = ? WHERE id = ?";

    private ConnectionData conn;

    public LineDAO(ConnectionData conn){

        this.conn = conn;
    }
    public LineDAO(){

        this.conn = ConnectionMySQL.getConnect();
    }

    @Override
    public List<Line> findAll() throws SQLException {
        List<Line> lines = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Line l = new Line();
                l.setId(rs.getInt("id"));
                l.setLine_name(rs.getString("line_name"));
                l.setPlace(rs.getString("place"));
                l.setRoute(rs.getString("route"));
                l.setTimetable(rs.getString("timetable"));
                lines.add(l);
            }
        }
        return lines;
    }

    @Override
    public Line findById(String id) throws SQLException {
        return null;
    }

    @Override
    public Line insert(Line entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Line entity) throws SQLException {

    }

    @Override
    public Line update(Line entity) throws SQLException {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
