package org.ARuiz.Model.Domain;

import java.util.Objects;

public class Admin extends Person{
    private String name;
    private int id_admin;
    private String password;
    private String phone;

    public Admin(String name, int id_admin, String password, String phone) {
        this.name = name;
        this.id_admin = id_admin;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Admin admin = (Admin) o;

        if (id_admin != admin.id_admin) return false;
        if (!Objects.equals(name, admin.name)) return false;
        if (!Objects.equals(password, admin.password)) return false;
        return Objects.equals(phone, admin.phone);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + id_admin;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", id_admin=" + id_admin +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
