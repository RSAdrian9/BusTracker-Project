package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.Stop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la interfaz DAO para la entidad StopAdmin.
 */
public class StopDAO implements DAO<Stop> {
    private final static String FINDALL = "SELECT * FROM stop"; // Buscar parada entera
    private final static String FINDBYID = "SELECT * FROM stop WHERE id_stop = ?"; // Buscar por id de parada
    private final static String FINDBYNAME = "SELECT name FROM stop WHERE name = ?"; // Buscar por nombre de parada
    private final static String INSERT = "INSERT INTO stop (name) VALUES (?)"; // Insertar parada
    private final static String UPDATE = "UPDATE stop SET name = ? WHERE id_stop = ?"; // Actualizar parada
    private final static String DELETE = "DELETE FROM stop WHERE id_stop = ?"; // Eliminar parada


    private final static String FINDSTOPSBYLINE = "SELECT s.id_stop, s.name FROM stop s JOIN line_stop ls ON s.id_stop = ls.id_stop WHERE ls.id_bus = ?";private final static String FINDLINESBYSTOP = "SELECT l.id_bus, l.name, l.place FROM line l JOIN line_stop ls ON l.id_bus = ls.id_bus WHERE ls.id_stop = ?";

    private Connection con;

    /**
     * Constructor de la clase StopDAO que utiliza la conexión por defecto.
     */
    public StopDAO(Connection con) {
        this.con = ConnectionMySQL.getConnect();
        if (this.con == null) {
            throw new IllegalStateException("Error: Connection is null");
        }
    }

    /**
     * Establece la conexión a la base de datos.
     *
     * @param con La conexión a establecer.
     */
    public void setConnection(Connection con) {
        this.con = con;
    }


    /**
     * @author Adrián Ruiz Sánchez
     * @return stops
     * @throws SQLException
     */
    @Override
    public List<Stop> findAll() throws SQLException {
        List<Stop> stops = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(FINDALL)) {
            ResultSet rs = pst.executeQuery();

            LineDAO ldao = new LineDAO(con);
            while (rs.next()) {
                Stop stop = new Stop();
                int stopId = rs.getInt("id_stop");
                String stopName = rs.getString("name");
                stop.setId_stop(stopId);
                stop.setName(stopName);
                stop.setLineas(null);
                stops.add(stop);
            }
        }
        return stops;
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @param id
     * @return stop
     * @throws SQLException
     */

    @Override
    public Stop findById(int id) throws SQLException {
        Stop stop = null;

        try (PreparedStatement pst = con.prepareStatement(FINDBYID)) {
            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                LineDAO linedao = new LineDAO(con);
                while (rs.next()) {
                    if (stop == null) {
                        stop = new Stop();
                        stop.setId_stop(rs.getInt("id_stop"));
                        stop.setName(rs.getString("name"));

                        stop.setLineas(null);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stop;
    }


    public List<Stop> findStopsByLine(int lineId) throws SQLException {
        List<Stop> stops = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(FINDSTOPSBYLINE)) {
            pst.setInt(1, lineId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Stop stop = new Stop(
                            rs.getInt("id_stop"),
                            rs.getString("name")
                    );
                    stops.add(stop);
                }
            }
        }
        return stops;
    }


    /**
     * @author Adrián Ruiz Sánchez
     * @param entity
     * @return entity
     * @throws SQLException
     */
    @Override
    public Stop insert(Stop entity) throws SQLException {

        Stop existingStop = findById(entity.getId_stop());
        if (existingStop != null) {
            throw new SQLException("A stop with the same ID already exists");
        }
        existingStop = findByName(entity.getName());
        if (existingStop != null) {
            throw new SQLException("A stop with the same name already exists");
        }
        try (PreparedStatement pst = con.prepareStatement(INSERT)) {
            pst.setString(1, entity.getName());
            pst.executeUpdate();
        }
        return entity;
    }

    /**
     * @param entity
     * @return entity
     * @throws SQLException
     * @author Adrián Ruiz Sánchez
     */
    @Override
    public Stop delete(Stop entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId_stop());
            pst.executeUpdate();
        }
        return entity;
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @param entity
     * @return entity
     * @throws SQLException
     */
    @Override
    public Stop update(Stop entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, entity.getName());
            pst.setInt(2, entity.getId_stop());
            pst.executeUpdate();
        }
        return entity;
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @param name
     * @return null
     * @throws SQLException
     */
    private Stop findByName(String name) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(FINDBYNAME)) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Stop(
                        rs.getInt("1"), rs.getString("name")
                );
            }
            return null;
        }
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        if (con != null) {
            con.close();
        }
    }
}


