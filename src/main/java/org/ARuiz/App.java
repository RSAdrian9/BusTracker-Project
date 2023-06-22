package org.ARuiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación que extiende de la clase Application de JavaFX.
 * Esta clase se encarga de iniciar y configurar la aplicación JavaFX, así como de gestionar la navegación entre las vistas de la aplicación.
 * @author Adrián Ruiz
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Método de inicio de la aplicación JavaFX. Crea la escena principal y muestra la ventana inicial.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML de la vista inicial.
     * @author Adrián Ruiz
     */

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginView"), 400, 560);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Establece la vista raíz de la aplicación con el archivo FXML especificado.
     *
     * @param fxml El nombre del archivo FXML de la vista a establecer como raíz.
     * @throws IOException Si ocurre un error al cargar el archivo FXML de la vista.
     * @author Adrián Ruiz
     */

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga un archivo FXML y devuelve el nodo raíz de la vista correspondiente.
     *
     * @param fxml El nombre del archivo FXML de la vista a cargar.
     * @return El nodo raíz de la vista cargada desde el archivo FXML.
     * @throws IOException Si ocurre un error al cargar el archivo FXML de la vista.
     * @author Adrián Ruiz
     */

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
