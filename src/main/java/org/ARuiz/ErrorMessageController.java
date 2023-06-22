package org.ARuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * La clase `ErrorMessageController` es un controlador para mostrar mensajes de error en una interfaz gráfica de usuario.
 * Utiliza JavaFX para la construcción de la interfaz.
 *
 * Esta clase proporciona métodos para establecer el mensaje de error y manejar la acción del botón de cierre.
 * También incluye un método para mostrar una vista de error separada en una nueva ventana.
 * @author Adrián Ruiz
 */
public class ErrorMessageController {
    @FXML
    private Label errorLabel;

    @FXML
    private Button closeButton;

    private Stage stage;

    /**
     * Establece el objeto `Stage` asociado a esta clase.
     *
     * @param stage El objeto `Stage` que representa la ventana en la que se muestra la interfaz gráfica.
     * @author Adrián Ruiz
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Establece el texto de la etiqueta de error con el mensaje de error proporcionado.
     *
     * @param errorMessage El mensaje de error a mostrar.
     * @author Adrián Ruiz
     */
    public void setErrorLabel(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    /**
     * Maneja la acción del botón de cierre. Obtiene la referencia al objeto `Stage` actual y lo cierra, lo que provoca el cierre de la ventana.
     * @author Adrián Ruiz
     */
    @FXML
    private void handleCloseButton() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra una nueva ventana de error con el mensaje proporcionado.
     *
     * @param errorMessage El mensaje de error a mostrar.
     * @author Adrián Ruiz
     */
    public void showErrorView(String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorMessageView.fxml"));
            Parent root = loader.load();
            ErrorMessageController errorController = loader.getController();
            errorController.setErrorLabel(errorMessage);

            Stage currentStage = (Stage) errorLabel.getScene().getWindow();

            Stage errorStage = new Stage();
            errorStage.initOwner(currentStage);
            errorStage.initStyle(StageStyle.UNDECORATED);
            errorStage.setScene(new Scene(root));
            errorStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




