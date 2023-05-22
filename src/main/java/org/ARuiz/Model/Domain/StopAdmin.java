package org.ARuiz.Model.Domain;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.value.ObservableValue;

import java.sql.Time;
import java.util.Objects;

public class StopAdmin {
    private int id_stop;
    private String name;

    public StopAdmin(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "StopAdmin{" +
                "id_stop=" + id_stop +
                ", name='" + name + '\'' +
                '}';
    }
}
