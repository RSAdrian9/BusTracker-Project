package org.ARuiz.Model.Domain;

import java.util.Objects;

public class Admin extends Person{
    private int id_admin;
    private String user;
    private String password;
    private String email;

    public Admin(int id_admin, String user, String password, String email) {
        this.id_admin = id_admin;
        this.user = user;
        this.password = password;
        this.email = email;
    }


    public Admin() {

    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Admin admin = (Admin) o;

        if (id_admin != admin.id_admin) return false;
        if (!Objects.equals(user, admin.user)) return false;
        if (!Objects.equals(password, admin.password)) return false;
        return Objects.equals(email, admin.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id_admin;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id_admin=" + id_admin +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
