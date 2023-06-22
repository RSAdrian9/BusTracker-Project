package org.ARuiz.Model.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una línea de autobús en el sistema.
 * @author Adrián Ruiz
 */
public class Line {
    private int id_bus;
    private String name;
    private int place;
    private List<Stop> stops;

    /**
     * Constructor de la clase Line.
     *
     * @param id_bus  Identificador de la línea de autobús.
     * @param name    Nombre de la línea de autobús.
     * @param place   Número de plazas disponibles en la línea de autobús.
     * @author Adrián Ruiz
     */
    public Line(int id_bus, String name, int place) {
        this.id_bus = id_bus;
        this.name = name;
        this.place = place;
        this.stops = new ArrayList<>();
    }

    /**
     * Constructor vacío de la clase Line.
     * Inicializa la lista de paradas.
     * @author Adrián Ruiz
     */
    public Line() {
        stops = new ArrayList<>();
    }

    /**
     * Constructor de la clase Line.
     *
     * @param name  Nombre de la línea de autobús.
     * @author Adrián Ruiz
     */
    public Line(String name) {
        this.name = name;
    }

    /**
     * Devuelve el identificador de la línea de autobús.
     *
     * @return Identificador de la línea de autobús.
     * @author Adrián Ruiz
     */
    public int getId_bus() {
        return id_bus;
    }

    /**
     * Establece el identificador de la línea de autobús.
     *
     * @param id_bus Identificador de la línea de autobús.
     * @author Adrián Ruiz
     */
    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    /**
     * Método getter para obtener el nombre de la línea.
     *
     * @return El nombre de la línea.
     * @author Adrián Ruiz
     */
    public String getName() {
        return name;
    }

    /**
     * Método setter para establecer el nombre de la línea.
     *
     * @param name El nombre de la línea.
     * @author Adrián Ruiz
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método getter para obtener el número de plazas disponibles en la línea.
     *
     * @return El número de plazas disponibles en la línea.
     * @author Adrián Ruiz
     */
    public int getPlace() {
        return place;
    }

    /**
     * Método setter para establecer el número de plazas disponibles en la línea.
     *
     * @param place El número de plazas disponibles en la línea.
     * @author Adrián Ruiz
     */
    public void setPlace(int place) {
        this.place = place;
    }

    /**
     * Método getter para obtener la lista de paradas asociadas a la línea.
     *
     * @return La lista de paradas asociadas a la línea.
     * @author Adrián Ruiz
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     * Método setter para establecer la lista de paradas asociadas a la línea.
     *
     * @param stops La lista de paradas asociadas a la línea.
     * @author Adrián Ruiz
     */
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    /**
     * Método toString para obtener una representación en cadena de la línea.
     * La representación incluye el identificador, el nombre y el número de plazas.
     *
     * @return La representación en cadena de la línea.
     * @author Adrián Ruiz
     */
    @Override
    public String toString() {
        return "Lines -> " + id_bus + " " + name + " " + place;
    }
}
