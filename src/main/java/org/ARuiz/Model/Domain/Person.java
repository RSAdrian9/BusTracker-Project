package org.ARuiz.Model.Domain;

import java.util.Objects;

/**
 * Clase abstracta que representa una persona en el sistema.
 * Cada persona tiene un número de identificación único (DNI).
 * @author Adrián Ruiz
 */
public abstract class Person {
    private String dni;

    /**
     * Método getter para obtener el número de identificación de la persona.
     *
     * @return El número de identificación de la persona (DNI).
     * @author Adrián Ruiz
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método setter para establecer el número de identificación de la persona.
     *
     * @param dni El número de identificación de la persona (DNI).
     * @author Adrián Ruiz
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Método equals para comparar si dos personas son iguales.
     * Dos personas son iguales si tienen el mismo número de identificación (DNI).
     *
     * @param o El objeto a comparar.
     * @return true si las personas son iguales, false en caso contrario.
     * @author Adrián Ruiz
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(dni, person.dni);
    }

    /**
     * Método hashCode para generar el código hash de la persona.
     * El código hash se basa en el número de identificación (DNI) de la persona.
     *
     * @return El código hash de la persona.
     * @author Adrián Ruiz
     */
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    /**
     * Método toString para obtener una representación en cadena de la persona.
     * La representación incluye el número de identificación (DNI).
     *
     * @return La representación en cadena de la persona.
     * @author Adrián Ruiz
     */
    @Override
    public String toString() {
        return "Person{" +
                "dni='" + dni + '\'' +
                '}';
    }
}

