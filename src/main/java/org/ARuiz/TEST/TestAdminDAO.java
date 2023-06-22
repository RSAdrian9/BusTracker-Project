package org.ARuiz.TEST;

import org.ARuiz.Model.DAO.AdminDAO;
import org.ARuiz.Model.Domain.Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TestAdminDAO {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bustracker";
        String user = "root";
        String pwd = "";

        try (Connection con = DriverManager.getConnection(url, user, pwd)) {

            AdminDAO adminDAO = new AdminDAO(con);

            // Insertar un nuevo administrador
            Admin newAdmin = new Admin();
            newAdmin.setUser("Adrian");
            newAdmin.setPassword("password");
            newAdmin.setEmail("emily@example.com");
            newAdmin=adminDAO.insert(newAdmin);  /// inserta en la base de datos y devuelvemelo completo, es decir, con el id
            System.out.println("Nuevo administrador insertado: " + newAdmin);
            System.out.println("ID generado: " + newAdmin.getId_admin());

            // Imprimir un párrafo en blanco
            System.out.println();

            // Obtener todos los administradores
            List<Admin> admins = adminDAO.findAll();
            System.out.println("Lista de administradores:");
            for (Admin admin : admins) {
                System.out.println(admin);
            }

            // Imprimir un párrafo en blanco
            System.out.println();

            // Obtener administrador por ID
            int id_admin = 18;
            Admin foundAdmin = adminDAO.findById(Integer.valueOf(id_admin));
            System.out.println("Administrador encontrado con ID " + id_admin + ": " + foundAdmin);

            // Imprimir un párrafo en blanco
            System.out.println();

            // Actualizar administrador
            foundAdmin.setPassword("1234");
            adminDAO.update(foundAdmin);
            System.out.println("Administrador actualizado: " + foundAdmin);

            // Imprimir un párrafo en blanco
            System.out.println();

            // Eliminar administrador
            adminDAO.delete(foundAdmin);
            System.out.println("Administrador eliminado: " + foundAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




