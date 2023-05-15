package org.ARuiz.Model.Domain;

import java.util.Objects;

public abstract class Person {
    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(dni, person.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Person{" +
                "dni='" + dni + '\'' +
                '}';
    }
}
