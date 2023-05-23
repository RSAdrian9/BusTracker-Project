package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.Domain.StopAdmin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la interfaz DAO para la entidad StopAdmin.
 */
public class StopDAO implements DAO<StopAdmin> {
    private final static String FINDALL = "SELECT * FROM stop"; // Buscar parada entera
    private final static String FINDBYID = "SELECT * FROM stop WHERE id_stop = ?"; // Buscar por id de parada
    private final static String FINDBYNAME = "SELECT name FROM stop WHERE name = ?"; // Buscar por nombre de parada
    private final static String INSERT = "INSERT INTO stop (name) VALUES (?)"; // Insertar parada
    private final static String UPDATE = "UPDATE stop SET name = ? WHERE id_stop = ?"; // Actualizar parada
    private final static String DELETE = "DELETE FROM stop WHERE id_stop = ?"; // Eliminar parada

    private Connection con;

    /**
     * Constructor de la clase StopDAO que acepta una conexión como parámetro.
     *
     * @param connection La conexión a la base de datos.
     */
    public StopDAO(Connection connection) {
        this.con = connection;
        if (this.con == null) {
            throw new IllegalStateException("Error: Connection is null");
        }
    }

    /**
     * Constructor de la clase StopDAO que utiliza la conexión por defecto.
     */
    public StopDAO() {
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
    public List<StopAdmin> findAll() throws SQLException {
        List<StopAdmin> stops = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(FINDALL)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                StopAdmin stop = new StopAdmin(
                        rs.getString("name")
                );
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
    public StopAdmin findById(int id) throws SQLException {
        StopAdmin stop = null;
        try (PreparedStatement pst = con.prepareStatement(FINDBYID)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                stop = new StopAdmin(
                        rs.getString("name")
                );
            }
        }
        return stop;
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @param entity
     * @return entity
     * @throws SQLException
     */
    @Override
    public StopAdmin insert(StopAdmin entity) throws SQLException {
        StopAdmin existingStop = findById(entity.getId_stop());
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
    public StopAdmin delete(StopAdmin entity) throws SQLException {
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
    public StopAdmin update(StopAdmin entity) throws SQLException {
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
    private StopAdmin findByName(String name) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(FINDBYNAME)) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new StopAdmin(
                        rs.getString("name")
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


