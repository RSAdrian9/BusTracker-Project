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
        btnLogoutValidate(null);
    }

    @FXML
    private void btnLogoutValidate(ActionEvent event) {
        try {
            App.setRoot("LoginView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showLines(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LineViewAdmin.fxml"));
            Parent lineViewAdmin = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(lineViewAdmin);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showBookings(ActionEvent event) {
    }

    public void showStops(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StopViewAdmin.fxml"));
            Parent stopViewAdmin = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(stopViewAdmin);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCinemasHamm(ActionEvent event) {
    }
}