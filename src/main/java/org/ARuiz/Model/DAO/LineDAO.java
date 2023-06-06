package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.Stop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineDAO implements DAO<Line> {
    private final static String FINDALL = "SELECT * FROM line";
    private final static String FINDBYID = "SELECT * FROM line WHERE id_bus = ?";
    private final static String INSERT = "INSERT INTO line (name, place) VALUES (?, ?)";
    private final static String DELETE = "DELETE FROM line WHERE id_bus = ?";
    private final static String UPDATE = "UPDATE line SET line_name = ?, place = ? WHERE id_bus = ?";


    private final static String ADDSTOPTOLINE = "INSERT INTO line_stop (id_bus, id_stop) VALUES (?, ?)";


    private Connection con;

    public LineDAO(Connection con) {

        this.con = con;
    }

    public LineDAO() {

        this.con = ConnectionMySQL.getConnect();
    }

    @Override
    public List<Line> findAll() throws SQLException {
        List<Line> lines = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(FINDALL);
             ResultSet rs = pst.executeQuery()) {

            StopDAO sdao = new StopDAO(con);
            while (rs.next()) {
                    Line line = new Line();
                    int lineId = rs.getInt("id_bus");
                    line.setId_bus(lineId);
                    line.setName(rs.getString("name"));
                    line.setPlace(rs.getInt("place"));
                    line.setStops(sdao.findStopsByLine(lineId)); // Crear una nueva lista de paradas para cada línea
                    lines.add(line);
                }
        }

        return lines;
    }


    @Override
    public Line findById(int id) throws SQLException {
        Line line = null;

        try (PreparedStatement pst = con.prepareStatement(FINDBYID)) {
            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                StopDAO stopdao = new StopDAO(con);
                while (rs.next()) {
                    if (line == null) {
                        line = new Line();
                        line.setId_bus(rs.getInt("id_bus"));
                        line.setName(rs.getString("name"));
                        line.setPlace(rs.getInt("place"));
                        //Invoca a StopDAO.getStopsByLine(id);

                        line.setStops(stopdao.findStopsByLine(id)); // Crear una nueva lista de paradas solo una vez
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la excepción para depurar el problema
        }

        return line;
    }


    /**
     * Método para añadir paradas a una línea en específico
     * @param lineId
     * @param stopId
     * @throws SQLException
     */
    public void addStopToLine(int lineId, int stopId) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(ADDSTOPTOLINE)) {
            pst.setInt(1, lineId);
            pst.setInt(2, stopId);
            pst.executeUpdate();
        }
    }



    @Override
    public Line insert(Line entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, entity.getName());
            pst.setInt(2, entity.getPlace());

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
    public Stop delete(Line entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId_bus());
            pst.executeUpdate();
        }
        return null;
    }

    @Override
    public Line update(Line entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, entity.getName());
            pst.setInt(2, entity.getPlace());
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
        if (con != null) {
            con.close();
        }
    }


}




