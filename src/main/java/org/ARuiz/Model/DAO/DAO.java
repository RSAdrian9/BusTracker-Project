package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Domain.Stop;

import java.sql.SQLException;
import java.util.List;

    /**
     * Interfaz genérica para el acceso a datos (Data Access Object - DAO).
     * Esta interfaz define los métodos básicos para realizar operaciones CRUD en la base de datos.
     *
     * @param <T> El tipo de entidad con la que se trabaja en el DAO.
     * @author Adrián Ruiz
     */

public interface DAO<T> extends AutoCloseable {
    /**
     * Recupera todas las entidades de la base de datos.
     *
     * @return Una lista de todas las entidades encontradas.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    List<T> findAll() throws SQLException;

    /**
     * Recupera una entidad por su identificador.
     *
     * @param id El identificador de la entidad a buscar.
     * @return La entidad encontrada, o null si no se encuentra ninguna entidad con el identificador especificado.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    T findById(int id) throws SQLException;

    /**
     * Inserta una nueva entidad en la base de datos.
     *
     * @param entity La entidad a insertar.
     * @return La entidad insertada con el identificador generado.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    T insert(T entity) throws SQLException;

    /**
     * Elimina una entidad de la base de datos.
     *
     * @param entity La entidad a eliminar.
     * @return La entidad eliminada.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    Stop delete(T entity) throws SQLException;

    /**
     * Actualiza una entidad en la base de datos.
     *
     * @param entity La entidad a actualizar.
     * @return La entidad actualizada.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    T update(T entity) throws SQLException;

}

