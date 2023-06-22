package org.ARuiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.ARuiz.Model.DAO.LineDAO;
import org.ARuiz.Model.DAO.StopDAO;
import org.ARuiz.Model.Domain.Stop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la vista de administración de paradas.
 * @author Adrián Ruiz
 */
public class StopControllerAdmin {
    @FXML
    private TableView<Stop> tableViewStops;
    @FXML
    private TableColumn<Stop, Integer> idColumn;
    @FXML
    private TableColumn<Stop, String> nameColumn;
    @FXML
    private TextField txtfld_searchId;
    @FXML
    private TextField txtfld_name;
    @FXML
    private Button btnDelete;

    private ErrorMessageController errorController;

    private Connection con;
    StopDAO stopDAO;
    LineDAO lineDAO;

    /**
     * Establece la conexión a la base de datos.
     *
     * @param con La conexión a establecer.
     * @author Adrián Ruiz
     */
    public void setConnection(Connection con) {
        this.con = con;
        stopDAO = new StopDAO(con);
    }

    private ObservableList<Stop> stopList;

    /**
     * Inicializa el controlador después de que se haya cargado el archivo FXML.
     * Configura la apariencia de la tabla y los eventos de selección.
     *
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    @FXML
    public void initialize() throws SQLException {
        errorController = new ErrorMessageController();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_stop"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableViewStops.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updateDeleteButtonState();
        });

        stopList = FXCollections.observableArrayList();
        tableViewStops.setItems(stopList);

        refreshStopList();
    }

    /**
     * Actualiza la lista de paradas obteniendo los datos de la base de datos.
     * Si ocurre un error, muestra un mensaje de error en la interfaz.
     * @author Adrián Ruiz
     */
    @FXML
    public void refreshStopList() {
        if (stopDAO == null) {
            stopDAO = new StopDAO(con);
        }
        try {
            List<Stop> stops = stopDAO.findAll();
            stopList.setAll(stops);

        } catch (SQLException e) {
            String errorMessage = "Error en la base de datos: " + e.getMessage();
            errorController.showErrorView(errorMessage);
            e.printStackTrace();
        }
    }


    /**
     * Cambia la vista a la página principal de la aplicación.
     *
     * @param event El evento de clic del botón.
     * @author Adrián Ruiz
     */
    @FXML
    public void showHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
            Parent lineViewAdmin = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(lineViewAdmin);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            String errorMessage = "Error en la base de datos: " + e.getMessage();
            errorController.showErrorView(errorMessage);
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la vista de paradas administrativas.
     *
     * @param event El evento de clic del botón.
     * @author Adrián Ruiz
     */
    @FXML
    public void refreshLineView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StopViewAdmin.fxml"));
            Parent stopViewAdmin = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(stopViewAdmin);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            String errorMessage = "Error en la base de datos: " + e.getMessage();
            errorController.showErrorView(errorMessage);
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el estado del botón de eliminación en función de si se ha seleccionado una parada.
     * @author Adrián Ruiz
     */
    @FXML
    private void updateDeleteButtonState() {
        Stop selectedStop = tableViewStops.getSelectionModel().getSelectedItem();
        boolean lineSelected = (selectedStop != null);
        btnDelete.setDisable(!lineSelected);
    }


    /**
     * Agrega una nueva parada a la base de datos y actualiza la lista y la tabla visual.
     *
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    @FXML
    public void addStop() throws SQLException {
        String name = txtfld_name.getText();
        List<Stop> stops = stopDAO.findAll();
        int lastId = stops.isEmpty() ? 0 : stops.get(stops.size() - 1).getId_stop();
        Stop newStop = new Stop(lastId + 1, name);
        stopDAO.insert(newStop);
        clearInputFields();
        refreshStopList();
    }


    /**
     * Actualiza el nombre de una parada seleccionada en la base de datos y actualiza la lista y la tabla visual.
     *
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    @FXML
    public void updateStop() throws SQLException {
        Stop selectedStop = tableViewStops.getSelectionModel().getSelectedItem();
        if (selectedStop != null) {
            String name = txtfld_name.getText();
            selectedStop.setName(name);
            stopDAO.update(selectedStop);
            clearInputFields();
            refreshStopList();
        }
    }

    /**
     * Elimina una parada seleccionada de la base de datos y actualiza la lista y la tabla visual.
     *
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    @FXML
    public void deleteStop() throws SQLException {
        Stop selectedStop = tableViewStops.getSelectionModel().getSelectedItem();
        if (selectedStop == null) {
            System.out.println("No se ha seleccionado ninguna parada.");
            return;
        }

        stopDAO.delete(selectedStop);
        stopList.remove(selectedStop);
        tableViewStops.refresh();
    }

    /**
     * Muestra información sobre las líneas asociadas a una parada seleccionada.
     * @author Adrián Ruiz
     */
    @FXML
    public void infoLinesByStop() {
        Stop selectedStop = tableViewStops.getSelectionModel().getSelectedItem();
        if (selectedStop != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/ARuiz/ShowLinesByStop.fxml"));
                Parent root = loader.load();

                LinesByStopController controller = loader.getController();
                controller.setConnection(con);
                controller.setLineDAO(new LineDAO());
                controller.setSelectedStop(selectedStop);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                String errorMessage = "Error en la base de datos: " + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();
            }
        }
    }

    /**
     * Busca una parada por su ID en la base de datos y muestra sus detalles en la interfaz.
     * @author Adrián Ruiz Sánchez
     */
    @FXML
    private void searchIdStop() {
        String searchId = txtfld_searchId.getText();
        if (!searchId.isEmpty()) {
            try {
                int id = Integer.parseInt(searchId);
                Stop stop = stopDAO.findById(id);
                if (stop != null) {
                    txtfld_name.setText(stop.getName());

                    tableViewStops.getSelectionModel().select(stop);
                    updateDeleteButtonState();
                } else {
                    System.out.println("No se encontró ninguna parada con el ID proporcionado");
                }

            } catch (NumberFormatException e) {
                String errorMessage = "Error en la base de datos: " + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();

            } catch (SQLException e) {
                String errorMessage = "Error en la base de datos: " + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();
            }
        } else {
            System.out.println("Se debe ingresar un ID de parada válido para buscar");
        }
    }


    /**
     * Limpia los campos de entrada de texto en la interfaz.
     * @author Adrián Ruiz Sánchez
     */
    private void clearInputFields() {
        txtfld_name.clear();
    }

}

