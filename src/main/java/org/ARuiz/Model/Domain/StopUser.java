package org.ARuiz.Model.Domain;

public enum StopUser {
    STOP_1("Estación de Autobuses de Córdoba"),
    STOP_2("Mezquita-Catedral"),
    STOP_3("Alcázar de los Reyes Cristianos"),
    STOP_4("Templo Romano"),
    STOP_5("Plaza de las Tendillas"),
    STOP_6("Puerta de Almodóvar"),
    STOP_7("Sinagoga"),
    STOP_8("Puerta del Puente"),
    STOP_9("Jardín Botánico"),
    STOP_10("Mercado Victoria"),
    STOP_11("Torre de la Calahorra"),
    STOP_12("Palacio de Viana"),
    STOP_13("Puente Romano"),
    STOP_14("Jardines del Alcázar"),
    STOP_15("Museo Arqueológico de Córdoba");

    private String name;

    StopUser(String name) {
        this.name = name;
    }

    public String getNameUser() {
        return name;
    }


}
