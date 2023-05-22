package org.ARuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LineControllerAdmin {

    @FXML
    private Label lbl_idAdmin;

    private int id_admin;

    public void setAdminId(int adminId) {
        this.id_admin = id_admin;
    }

    @FXML
    private void initialize () {
        lbl_idAdmin.setText(String.valueOf(id_admin));
    }

    @FXML
    public void showHome(ActionEvent event) {
        try {
            // Cargar la vista LineViewAdmin desde su archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
            Parent lineViewAdmin = loader.load();

            // Obtener el Stage actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Crear una nueva escena con la vista LineViewAdmin
            Scene scene = new Scene(lineViewAdmin);

            // Establecer la nueva escena en el Stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier excepción que pueda ocurrir durante la carga de la vista
        }
    }

    @FXML
    public void refreshLineView(ActionEvent event) {
        try {
            // Cargar la vista LineViewAdmin desde su archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LineViewAdmin.fxml"));
            Parent lineViewAdmin = loader.load();

            // Obtener el Stage actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Crear una nueva escena con la vista LineViewAdmin
            Scene scene = new Scene(lineViewAdmin);

            // Establecer la nueva escena en el Stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier excepción que pueda ocurrir durante la carga de la vista
        }
    }
}
