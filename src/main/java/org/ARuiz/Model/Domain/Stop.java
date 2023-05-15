package org.ARuiz.Model.Domain;

public enum Stop {
    STOP_1("Estación de Autobuses de Córdoba"), STOP_2("Stop 2"), STOP_3("Stop 3"), STOP_4("Stop 2"),
    STOP_5("Stop 2"), STOP_6("Stop 2"), STOP_7("Stop 2"), STOP_8("Stop 2"), STOP_9("Stop 2"),
    STOP_10("Stop 2"), STOP_11("Stop 2"), STOP_12("Stop 2"), STOP_13("Stop 2");


    private String Sname;

    Stop(String Sname) {
        this.Sname = Sname;
    }

    public String getSName() {
        return Sname;
    }
}
