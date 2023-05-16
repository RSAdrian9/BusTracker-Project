package org.ARuiz.Model.Domain;

import java.time.LocalTime;
import java.util.Objects;

public class Line {
    private int id_bus;
    private String line_name;
    private int place;
    private String route;
    private LocalTime timetable;

    public Line(int id_bus, String line_name, int place, String route, LocalTime timetable) {
        this.id_bus = id_bus;
        this.line_name = line_name;
        this.place = place;
        this.route = route;
        this.timetable = timetable;
    }

    public Line() {

    }

    public int getId_bus() {
        return id_bus;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public LocalTime getTimetable() {
        return timetable;
    }

    public void setTimetable(LocalTime timetable) {
        this.timetable = timetable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (id_bus != line.id_bus) return false;
        if (place != line.place) return false;
        if (!Objects.equals(line_name, line.line_name)) return false;
        if (!Objects.equals(route, line.route)) return false;
        return Objects.equals(timetable, line.timetable);
    }

    @Override
    public int hashCode() {
        int result = id_bus;
        result = 31 * result + (line_name != null ? line_name.hashCode() : 0);
        result = 31 * result + place;
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (timetable != null ? timetable.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id_bus=" + id_bus +
                ", line_name='" + line_name + '\'' +
                ", place=" + place +
                ", route='" + route + '\'' +
                ", timetable=" + timetable +
                '}';
    }
}
