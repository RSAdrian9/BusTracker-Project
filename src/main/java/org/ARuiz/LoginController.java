package org.ARuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.ARuiz.App;
import org.ARuiz.Model.Connections.ConnectionMySQL;
import org.ARuiz.Model.DAO.AdminDAO;
import org.ARuiz.Model.Domain.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField txtfld_username;

    @FXML
    private PasswordField txtfld_password;

    private Connection connection;


    public void initialize() {
        // Establecer la conexión a la base de datos
        connection = ConnectionMySQL.getConnect();
    }

    @FXML
    private void btnLoginValidate() throws IOException {
        if (!txtfld_username.getText().isEmpty() && !txtfld_password.getText().isEmpty()) {
            String user = txtfld_username.getText();
            String password = txtfld_password.getText();
            AdminDAO aDAO = new AdminDAO();
            boolean state = aDAO.getAdminByUsernameAndPassword(user, password);
            if (state) {
                App.setRoot("HomeView");
            }
        }

        /*String user = txtfld_username.getText();
        String password = txtfld_password.getText();

        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.getAdminByUsernameAndPassword(user, password);

        if (admin != null) {
            showAlert("Inicio de sesión exitoso", "¡Bienvenido " + user + "!");
            App.setRoot("HomeView.fxml");
        } else {
            showAlert("Inicio de sesión fallido", "Usuario o contraseña incorrectos");
        } */
    }


    @FXML
    private void btnSignupValidate(ActionEvent event) {
        String user = txtfld_username.getText();
        String password = txtfld_password.getText();

        if (createUser(user, password)) {
            showAlert("Cuenta creada", "¡La cuenta se ha creado exitosamente!");
        } else {
            showAlert("Error al crear cuenta", "No se pudo crear la cuenta. Inténtalo de nuevo.");
        }
    }


    private boolean authenticateUser(String user, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin WHERE user = ? AND password = ?");
            statement.setString(1, user);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Si el usuario existe en la base de datos, el resultado tendrá al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean createUser(String user, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO admin (user, password) VALUES (?, ?)");
            statement.setString(1, user);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0; // Si se insertó al menos una fila, se creó el usuario correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}






