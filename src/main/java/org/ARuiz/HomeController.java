package org.ARuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class HomeController {
    @FXML
    private Label idUser;

    @FXML
    private void initialize () {

    }

    @FXML
    private void btnLogoutValidate(ActionEvent event) {
        // Código para cerrar la sesión del usuario
        // Ejemplo: volver a la vista de Login

        try {
            App.setRoot("LoginView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showLines(ActionEvent event) {
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


    public void showBookings(ActionEvent event) {
    }

    public void showStops(ActionEvent event) {
        try {
            // Cargar la vista LineViewAdmin desde su archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StopViewAdmin.fxml"));
            Parent stopViewAdmin = loader.load();

            // Obtener el Stage actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Crear una nueva escena con la vista LineViewAdmin
            Scene scene = new Scene(stopViewAdmin);

            // Establecer la nueva escena en el Stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier excepción que pueda ocurrir durante la carga de la vista
        }
    }

    public void showCinemasHamm(ActionEvent event) {
    }








    /*
    @FXML
    private void initialize() {
        // Aquí puedes agregar la lógica de inicialización del controlador, si es necesario
        btnAccessLines.setOnAction(event -> {
            openLineView();
        });

        btnAccessBookings.setOnAction(event -> {
            openBookingView();
        });

        btnAccessStops.setOnAction(event -> {
            openStopView();
        });

        btnAccessCinemasHamm.setOnAction(event -> {
            openCinemaView();
        });
    }

    private void openLineView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LinesViewAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnAccessLines.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openBookingView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingsViewAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnAccessBookings.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openStopView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StopsViewAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnAccessStops.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openCinemaView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CinemasView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnAccessCinemasHamm.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } */

}