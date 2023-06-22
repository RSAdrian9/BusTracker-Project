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

/**
 * La clase `HomeController` es un controlador para la vista principal de la aplicación.
 * Utiliza JavaFX para la construcción de la interfaz gráfica.
 *
 * Esta clase proporciona métodos para inicializar la vista, manejar eventos de botones y mostrar diferentes vistas en función de las acciones del usuario.
 * @author Adrián Ruiz
 */
public class HomeController {

    /**
     * Inicializa la vista principal.
     * Se llama automáticamente cuando se carga la interfaz gráfica.
     * @author Adrián Ruiz
     */
    @FXML
    private void initialize () {

        btnLogoutValidate(null);
    }

    /**
     * Maneja el evento del botón de cierre de sesión.
     * Redirige al usuario a la vista de inicio de sesión.
     *
     * @param event El evento de acción del botón.
     * @author Adrián Ruiz
     */
    @FXML
    private void btnLogoutValidate(ActionEvent event) {
        try {
            App.setRoot("LoginView");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista de líneas.
     * Se llama cuando el usuario selecciona la opción de ver las líneas.
     *
     * @param event El evento de acción del botón.
     * @author Adrián Ruiz
     */
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

    /**
     * Muestra la vista de reservas.
     * Se llama cuando el usuario selecciona la opción de ver las reservas.
     *
     * @param event El evento de acción del botón.
     * @author Adrián Ruiz
     */
    public void showBookings(ActionEvent event) {
        // Implementación pendiente
    }

    /**
     * Muestra la vista de paradas.
     * Se llama cuando el usuario selecciona la opción de ver las paradas.
     *
     * @param event El evento de acción del botón.
     * @author Adrián Ruiz
     */
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

    /**
     * Muestra la vista de cines de Hamm.
     * Se llama cuando el usuario selecciona la opción de ver los cines de Hamm.
     *
     * @param event El evento de acción del botón.
     * @author Adrián Ruiz
     */
    public void showCinemasHamm(ActionEvent event) {
        // Implementación pendiente
    }
}