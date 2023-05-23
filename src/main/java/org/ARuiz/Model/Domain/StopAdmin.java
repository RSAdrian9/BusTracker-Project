package org.ARuiz.Model.Domain;

public class StopAdmin {
    private int id_stop;
    private String name;

    public StopAdmin(int id_stop, String name) {
        this.name = name;
        this.id_stop = id_stop;
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
