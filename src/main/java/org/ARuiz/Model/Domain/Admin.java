package org.ARuiz.Model.Domain;

/**
 * Clase que representa a un administrador en el sistema.
 * Hereda de la clase Person, que representa a una persona genérica.
 * @author Adrián Ruiz
 */
public class Admin extends Person{
    private int id_admin;
    private String user;
    private String password;
    private String email;

    /**
     * Constructor de la clase Admin.
     *
     * @param id_admin  El identificador del administrador.
     * @param user      El nombre de usuario del administrador.
     * @param password  La contraseña del administrador.
     * @param email     El correo electrónico del administrador.
     * @author Adrián Ruiz
     */
    public Admin(int id_admin, String user, String password, String email) {
        this.id_admin = id_admin;
        this.user = user;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructor vacío de la clase Admin.
     * @author Adrián Ruiz
     */
    public Admin() {

    }

    /**
     * Obtiene el identificador del administrador.
     *
     * @return El identificador del administrador.
     * @author Adrián Ruiz
     */
    public int getId_admin() {
        return id_admin;
    }

    /**
     * Establece el identificador del administrador.
     *
     * @param id_admin El identificador del administrador.
     * @author Adrián Ruiz
     */
    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    /**
     * Obtiene el nombre de usuario del administrador.
     *
     * @return El nombre de usuario del administrador.
     * @author Adrián Ruiz
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el nombre de usuario del administrador.
     *
     * @param user El nombre de usuario del administrador.
     * @author Adrián Ruiz
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene la contraseña del administrador.
     *
     * @return La contraseña del administrador.
     * @author Adrián Ruiz
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del administrador.
     *
     * @param password La contraseña del administrador.
     * @author Adrián Ruiz
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el correo electrónico del administrador.
     *
     * @return El correo electrónico del administrador.
     * @author Adrián Ruiz
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del administrador.
     *
     * @param email El correo electrónico del administrador.
     * @author Adrián Ruiz
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve una representación en forma de cadena de texto del objeto Admin.
     *
     * @return Una cadena de texto que representa al objeto Admin.
     * @author Adrián Ruiz
     */
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
