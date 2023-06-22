package org.ARuiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.ARuiz.Model.DAO.StopDAO;
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.Stop;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * La clase StopsByLineController representa el controlador para la vista que muestra las paradas
 * asociadas a una línea de autobús específica.
 * Administra la interacción entre los componentes de la interfaz de usuario y la capa de acceso a datos,
 * permitiendo al usuario ver las paradas y cerrar la vista.
 * @author Adrián Ruiz
 */
public class StopsByLineController {
    @FXML
    private ListView<Stop> stopListView;
    @FXML
    private Button closeButton;

    private ErrorMessageController errorController;

    private Connection con;
    private StopDAO stopDAO;
    private Line selectedLine;

    /**
     * Establece la conexión con la base de datos.
     *
     * @param con La conexión con la base de datos que se establecerá.
     * @author Adrián Ruiz
     */
    public void setConnection(Connection con) {

        this.con = con;
    }

    /**
     * Establece el objeto StopDAO para acceder a las operaciones de la base de datos relacionadas con las paradas.
     *
     * @param stopDAO El objeto StopDAO que se establecerá.
     * @author Adrián Ruiz
     */
    public void setStopDAO(StopDAO stopDAO) {

        this.stopDAO = stopDAO;
    }

    /**
     * Establece la línea de autobús seleccionada para mostrar las paradas asociadas.
     *
     * @param selectedLine La línea de autobús seleccionada.
     * @author Adrián Ruiz
     */
    public void setSelectedLine(Line selectedLine) {
        this.selectedLine = selectedLine;
        initializeListView();
    }

    /**
     * Inicializa la vista de lista de paradas.
     * Muestra las paradas asociadas a la línea de autobús seleccionada.
     * @author Adrián Ruiz
     */
    @FXML
    private void initializeListView() {
        errorController = new ErrorMessageController();

        if (selectedLine != null) {
            try {
                List<Stop> stops = stopDAO.findStopsByLine(selectedLine.getId_bus());
                ObservableList<Stop> stopList = FXCollections.observableArrayList(stops);
                stopListView.setItems(stopList);

            } catch (SQLException e) {
                String errorMessage = "Error al obtener la lista de lineas" + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();
            }
        }
    }

    /**
     * Cierra la vista actual.
     * @author Adrián Ruiz
     */
    @FXML
    public void closeView() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}





