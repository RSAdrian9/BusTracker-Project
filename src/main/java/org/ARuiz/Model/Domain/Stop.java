package org.ARuiz.Model.Domain;

import java.util.List;

public class Stop {
    private int id_stop;
    private String name;
    private List<Line> lineas;

    public Stop(int id_stop, String name) {
        this.id_stop = id_stop;
        this.name = name;
    }

    public Stop() {

    }

    public Stop(String s) {
    }

    public int getId_stop() {
        return id_stop;
    }

    public void setId_stop(int id_stop) {
        this.id_stop = id_stop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Line> getLineas() {
        return lineas;
    }

    public void setLineas(List<Line> lineas) {
        this.lineas = lineas;
    }


    @Override
    public String toString() {
        return "Stops -> " + id_stop + " " + name;
    }

    /*
    @Override
    public String toString() {
        return "Stop{" +
                "id_stop=" + id_stop +
                ", name='" + name + '\'' +
                ", lineas=" + lineas +
                '}';
    }

     */
}
