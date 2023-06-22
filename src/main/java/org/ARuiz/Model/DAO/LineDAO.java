package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.Stop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la interfaz DAO para la entidad Line.
 * Proporciona métodos para acceder a la base de datos y realizar operaciones CRUD en la tabla "line".
 * @author Adrián Ruiz
 */
public class LineDAO implements DAO<Line> {
    private final static String FINDALL = "SELECT * FROM line";
    private final static String FINDBYID = "SELECT * FROM line WHERE id_bus = ?";
    private final static String INSERT = "INSERT INTO line (name, place) VALUES (?, ?)";
    private final static String DELETE = "DELETE FROM line WHERE id_bus = ?";
    private final static String UPDATE = "UPDATE line SET name = ?, place = ? WHERE id_bus = ?";

    private final static String FINDLINESBYSTOP = "SELECT l.id_bus, l.name, l.place FROM line l JOIN line_stop ls ON l.id_bus = ls.id_bus WHERE ls.id_stop = ?";
    public final static String ADDSTOPTOLINE = "INSERT INTO line_stop (id_bus, id_stop) VALUES (?, ?)";

    /**
     * Constructor de la clase LineDAO que toma una conexión de base de datos existente.
     *
     * @param con La conexión a la base de datos.
     * @author Adrián Ruiz
     */
    private Connection con;

    /**
     * Constructor de la clase LineDAO que obtiene una conexión de base de datos utilizando la clase ConnectionMySQL.
     * @author Adrián Ruiz
     */
    public LineDAO(Connection con) {
        this.con = con;
    }

    public LineDAO() {
        this.con = ConnectionMySQL.getConnect();
    }

    /**
     * Recupera todas las líneas de la base de datos.
     *
     * @return Una lista de objetos Line que representan todas las líneas en la base de datos.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
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
        } catch (SQLException e) {

        }

        return lines;
    }

    /**
     * Recupera una línea de la base de datos por su identificador.
     *
     * @param id El identificador de la línea.
     * @return El objeto Line correspondiente al identificador especificado, o null si no se encuentra.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
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
     * Recupera todas las líneas asociadas a una parada específica.
     *
     * @param stopId El identificador de la parada.
     * @return Una lista de líneas asociadas a la parada.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    public List<Line> findLinesByStop(int stopId) throws SQLException {
        List<Line> lines = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(FINDLINESBYSTOP)) {
            pst.setInt(1, stopId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Line line = new Line(
                            rs.getInt("id_bus"),
                            rs.getString("name"),
                            rs.getInt("place")
                    );
                    lines.add(line);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Imprimir la excepción para depurar el problema
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la excepción para depurar el problema
        }
        return lines;
    }


    /**
     * Método para añadir una parada a una línea específica.
     *
     * @param lineId El identificador de la línea.
     * @param stopId El identificador de la parada.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    public void addStopToLine(int lineId, int stopId) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(ADDSTOPTOLINE)) {
            pst.setInt(1, lineId);
            pst.setInt(2, stopId);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la excepción para depurar el problema
        }
    }


    /**
     * Inserta una nueva línea en la base de datos.
     *
     * @param entity El objeto Line que se va a insertar.
     * @return El objeto Line insertado con su identificador generado.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
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

            } catch (SQLException e) {
                e.printStackTrace(); // Imprimir la excepción para depurar el problema
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la excepción para depurar el problema
        }
        return entity;
    }

    /**
     * Elimina una línea de la base de datos.
     *
     * @param entity El objeto Line que se va a eliminar.
     * @return El objeto Stop correspondiente a la última parada de la línea eliminada, o null si no se encuentra.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    @Override
    public Stop delete(Line entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId_bus());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la excepción para depurar el problema
        }

        return null;
    }

    /**
     * Actualiza una línea en la base de datos.
     *
     * @param entity El objeto Line que se va a actualizar.
     * @return El objeto Line actualizado.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    @Override
    public Line update(Line entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, entity.getName());
            pst.setInt(2, entity.getPlace());
            pst.setInt(3, entity.getId_bus());

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating line failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la excepción para depurar el problema
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




