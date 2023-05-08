package org.ARuiz.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtfld_username;

    @FXML
    private PasswordField txtfld_password;

    @FXML
    private Label lbl_errorMsg;

    @FXML
    void btnLoginValidate(ActionEvent event) {
        String username = txtfld_username.getText();
        String password = txtfld_password.getText();

        // Aquí puedes validar el nombre de usuario y la contraseña
        // Si son correctos, puedes mostrar la siguiente pantalla usando
        // un nuevo FXML y el siguiente código:
        // Parent root = FXMLLoader.load(getClass().getResource("nuevo.fxml"));
        // Scene scene = new Scene(root);
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // stage.setScene(scene);
        // stage.show();
        // Si no son correctos, muestra un mensaje de error:
        lbl_errorMsg.setText("Error: nombre de usuario o contraseña incorrectos");
        lbl_errorMsg.setVisible(true);
    }

    @FXML
    void btnSignupValidate(ActionEvent event) {
        // Aquí puedes agregar código para mostrar la pantalla de registro
        // usando un nuevo FXML y un nuevo controlador
    }

    @FXML
    void txtfldOnClick(ActionEvent event) {
        // Este método se llama cuando se hace clic en el cuadro de texto del nombre de usuario
        // o en el cuadro de texto de la contraseña. Aquí puedes agregar cualquier código que
        // desees ejecutar en respuesta a ese evento.
    }

}


