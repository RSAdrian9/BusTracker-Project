package org.ARuiz.Model.Domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private int id_bus;
    private String name;
    private int place;
    private List<Stop> stops;

    public Line(int id_bus, String name, int place) {
        this.id_bus = id_bus;
        this.name = name;
        this.place = place;
    }

    public Line() {
        stops = new ArrayList<>();
    }

    public int getId_bus() {
        return id_bus;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id_bus=" + id_bus +
                ", name='" + name + '\'' +
                ", place=" + place +
                ", stops=" + stops +
                '}';
    }
}
