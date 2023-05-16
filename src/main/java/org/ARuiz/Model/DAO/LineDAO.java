package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Connections.ConnectionData;
import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.Domain.Line;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LineDAO implements DAO<Line> {
    private final static String FINDALL = "SELECT * FROM line";
    private final static String FINDBYID = "SELECT * FROM line WHERE id = ?";
    private final static String INSERT = "INSERT INTO line (line_name, place, route, timetable) VALUES (?, ?, ?, ?)";
    private final static String DELETE = "DELETE FROM line WHERE id = ?";
    private final static String UPDATE = "UPDATE line SET line_name = ?, place = ?, route = ?, timetable = ? WHERE id = ?";

    private Connection conn;

    public LineDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Line> findAll() throws SQLException {
        List<Line> lines = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Line line = new Line();
                line.setId_bus(rs.getInt("id_bus"));
                line.setLine_name(rs.getString("line_name"));
                line.setPlace(rs.getInt("place"));
                line.setRoute(rs.getString("route"));
                line.setTimetable(LocalTime.parse(rs.getString("timetable")));
                lines.add(line);
            }
        }
        return lines;
    }

    @Override
    public Line findById(String id) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(FINDBYID)) {
            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Line line = new Line();
                    line.setId_bus(rs.getInt("id_bus"));
                    line.setLine_name(rs.getString("line_name"));
                    line.setPlace(rs.getInt("place"));
                    line.setRoute(rs.getString("route"));
                    line.setTimetable(LocalTime.parse(rs.getString("timetable")));
                    return line;
                }
            }
        }
        return null;
    }

    @Override
    public Line insert(Line entity) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, entity.getLine_name());
            pst.setInt(2, entity.getPlace());
            pst.setString(3, entity.getRoute());
            pst.setTime(4, Time.valueOf(entity.getTimetable()));

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting line failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId_bus(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Inserting line failed, no ID obtained.");
                }
            }
        }
        return entity;
    }

    @Override
    public void delete(Line entity) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId_bus());
            pst.executeUpdate();
        }
    }

    @Override
    public Line update(Line entity) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(UPDATE)) {
            pst.setString(1, entity.getLine_name());
            pst.setInt(2, entity.getPlace());
            pst.setString(3, entity.getRoute());
            pst.setTime(4, Time.valueOf(entity.getTimetable()));
            pst.setInt(5, entity.getId_bus());

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating line failed, no rows affected.");
            }
        }
        return entity;
    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

}




