package org.ARuiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.ARuiz.Model.DAO.LineDAO;
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.Stop;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * El controlador de la vista que muestra las líneas asociadas a una parada específica.
 * Esta clase se encarga de obtener y mostrar la lista de líneas relacionadas a una parada dada.
 * También permite cerrar la vista correspondiente.
 * @author Adrián Ruiz
 */
public class LinesByStopController {
    @FXML
    private ListView<Line> lineListView;
    @FXML
    private Button closeButton;

    private ErrorMessageController errorController;
    private Connection con;
    private LineDAO lineDAO;
    private Stop selectedStop;

    /**
     * Establece la conexión a la base de datos.
     *
     * @param con La conexión a establecer.
     * @author Adrián Ruiz
     */
    public void setConnection(Connection con) {
        this.con = con;
    }

    /**
     * Establece el objeto LineDAO utilizado para acceder a la base de datos de líneas.
     *
     * @param lineDAO El LineDAO a establecer.
     * @author Adrián Ruiz
     */
    public void setLineDAO(LineDAO lineDAO) {
        this.lineDAO = lineDAO;
    }

    /**
     * Establece la parada seleccionada para mostrar las líneas asociadas.
     * Inicializa la vista de lista de líneas.
     *
     * @param selectedStop La parada seleccionada.
     * @author Adrián Ruiz
     */
    public void setSelectedStop(Stop selectedStop) {
        this.selectedStop = selectedStop;
        initializeListView();
    }

    /**
     * Inicializa la vista de lista de líneas.
     * Muestra la lista de líneas relacionadas a la parada seleccionada.
     * En caso de error, muestra un mensaje de error y registra la excepción.
     * @author Adrián Ruiz
     */
    @FXML
    private void initializeListView() {
        errorController = new ErrorMessageController();

        if (selectedStop != null) {
            try {
                List<Line> lines = lineDAO.findLinesByStop(selectedStop.getId_stop());
                ObservableList<Line> lineList = FXCollections.observableArrayList(lines);
                lineListView.setItems(lineList);

            } catch (SQLException e) {
                String errorMessage = "Error al obtener la lista de paradas" + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();
            }
        }
    }

    /**
     * Cierra la vista actual.
     * Cierra la ventana correspondiente a la vista actual.
     * @author Adrián Ruiz
     */
    @FXML
    public void closeView() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

