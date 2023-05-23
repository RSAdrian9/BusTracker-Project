package org.ARuiz.Model.DAO;

import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.Domain.Admin;
import org.ARuiz.Model.Domain.StopAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements DAO<Admin> {
    private final static String FINDALL = "SELECT * FROM admin";
    private final static String FINDBYID = "SELECT * FROM admin WHERE id_admin = ?";
    private final static String INSERT = "INSERT INTO admin (user, password, email) VALUES (?, ?, ?)";
    private final static String DELETE = "DELETE FROM admin WHERE id_admin = ?";
    private final static String UPDATE = "UPDATE admin SET user = ?, password = ?, email = ? WHERE id_admin = ?";
    private final static String SELECTLOGIN = "SELECT * FROM admin WHERE user = ? AND password = ?";

    private Connection con;

    public AdminDAO(Connection con) {
        this.con = con;
    }

    public AdminDAO() {
        this.con = ConnectionMySQL.getConnect();
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(FINDALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Admin admin = new Admin();
                        admin.setId_admin(rs.getInt("id_admin"));
                        admin.setUser(rs.getString("user"));
                        admin.setPassword(rs.getString("password"));
                        admin.setEmail(rs.getString("email"));
                        admins.add(admin);
            }
        }
        return admins;
    }


    public Admin findById(int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(FINDBYID)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin(
                            rs.getInt("id_admin"),
                            rs.getString("user"),
                            rs.getString("password"),
                            rs.getString("email")
                    );
                    return admin;
                }
            }
        }
        return null;
    }



    @Override
    public Admin insert(Admin entity) throws SQLException {
        // Código de validación de duplicados omitido para mayor claridad

        try (PreparedStatement pst = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, entity.getUser());
            pst.setString(2, entity.getPassword());
            pst.setString(3, entity.getEmail());

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting admin failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    entity.setId_admin(generatedId);  // Asignar el ID generado al objeto Admin
                } else {
                    throw new SQLException("Inserting admin failed, no ID obtained.");
                }
            }
        }
        return entity;
    }


    @Override
    public StopAdmin delete(Admin entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId_admin());
            pst.executeUpdate();
        }
        return null;
    }

    @Override
    public Admin update(Admin entity) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, entity.getUser());
            pst.setString(2, entity.getPassword());
            pst.setString(3, entity.getEmail());
            pst.setInt(4, entity.getId_admin());

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating admin failed, no rows affected.");
            }
        }
        return entity;
    }

    public boolean getAdminByUsernameAndPassword(String user, String password) {
        boolean log = false;
        try (PreparedStatement pst = con.prepareStatement(SELECTLOGIN)) {
            pst.setString(1, user);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                log = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return log;
    }

    @Override
    public void close() throws Exception {
        if (con != null) {
            con.close();
        }
    }
}



