package org.ARuiz.Model.Domain;

import java.util.List;

/**
 * Clase que representa una parada en el sistema.
 * Cada parada tiene un identificador único y un nombre.
 * Además, puede estar asociada a varias líneas de autobús.
 * @author Adrián Ruiz
 */
public class Stop {
    private int id_stop;
    private String name;
    private List<Line> lineas;

    /**
     * Constructor de la clase Stop.
     *
     * @param id_stop El identificador de la parada.
     * @param name    El nombre de la parada.
     * @author Adrián Ruiz
     */
    public Stop(int id_stop, String name) {
        this.id_stop = id_stop;
        this.name = name;
    }

    /**
     * Constructor vacío de la clase Stop.
     * @author Adrián Ruiz
     */
    public Stop() {

    }

    /**
     * Constructor de la clase Stop.
     *
     * @param name El nombre de la parada.
     * @author Adrián Ruiz
     */
    public Stop(String name) {
        this.name = name;
    }

    /**
     * Método getter para obtener el identificador de la parada.
     *
     * @return El identificador de la parada.
     * @author Adrián Ruiz
     */
    public int getId_stop() {
        return id_stop;
    }

    /**
     * Método setter para establecer el identificador de la parada.
     *
     * @param id_stop El identificador de la parada.
     * @author Adrián Ruiz
     */
    public void setId_stop(int id_stop) {
        this.id_stop = id_stop;
    }

    /**
     * Método getter para obtener el nombre de la parada.
     *
     * @return El nombre de la parada.
     * @author Adrián Ruiz
     */
    public String getName() {
        return name;
    }

    /**
     * Método setter para establecer el nombre de la parada.
     *
     * @param name El nombre de la parada.
     * @author Adrián Ruiz
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método getter para obtener las líneas asociadas a la parada.
     *
     * @return La lista de líneas asociadas a la parada.
     * @author Adrián Ruiz
     */
    public List<Line> getLineas() {
        return lineas;
    }

    /**
     * Método setter para establecer las líneas asociadas a la parada.
     *
     * @param lineas La lista de líneas asociadas a la parada.
     * @author Adrián Ruiz
     */
    public void setLineas(List<Line> lineas) {
        this.lineas = lineas;
    }

    /**
     * Método toString para obtener una representación en cadena de la parada.
     * La representación incluye el identificador y el nombre de la parada.
     *
     * @return La representación en cadena de la parada.
     * @author Adrián Ruiz
     */
    @Override
    public String toString() {
        return "Stops -> " + id_stop + " " + name;
    }
}

