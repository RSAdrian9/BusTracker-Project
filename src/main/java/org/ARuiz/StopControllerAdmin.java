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
import org.ARuiz.Model.DAO.StopDAO;
import org.ARuiz.Model.Domain.StopAdmin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la vista de administración de paradas.
 */
public class StopControllerAdmin {
    @FXML
    private TableView<StopAdmin> tableViewStops;
    @FXML
    private TableColumn<StopAdmin, Integer> idColumn;
    @FXML
    private TableColumn<StopAdmin, String> nameColumn;
    @FXML
    private TextField txtfld_searchId;
    @FXML
    private TextField txtfld_name;

    private Connection con;
    StopDAO stopDAO;

    /**
     * Establece la conexión a la base de datos.
     *
     * @param con La conexión a establecer.
     */
    public void setConnection(Connection con) {
        this.con = con;
        stopDAO = new StopDAO();
    }

    private ObservableList<StopAdmin> stopList;

    public void initialize() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_stop"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        stopList = FXCollections.observableArrayList();
        tableViewStops.setItems(stopList);

        refreshStopList();
    }

    /**
     * Actualiza la lista de paradas.
     */
    public void refreshStopList() {
        if (stopDAO == null) {
            stopDAO = new StopDAO(); // Crear una nueva instancia de StopDAO
        }
        try {
            List<StopAdmin> stops = stopDAO.findAll();
            stopList.setAll(stops);
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de la excepción
        }
    }


    /**
     * @author Adrián Ruiz Sánchez
     * @param event
     */
    public void showHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
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
     * @author Adrián Ruiz Sánchez
     * @param event
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
            e.printStackTrace();
        }
    }


    /**
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
     */
    @FXML
    public void addStop() throws SQLException {
        String name = txtfld_name.getText();
        List<StopAdmin> stops = stopDAO.findAll();
        int lastId = stops.isEmpty() ? 0 : stops.get(stops.size() - 1).getId_stop();
        StopAdmin newStop = new StopAdmin(lastId + 1, name);
        stopDAO.insert(newStop);
        clearInputFields();
        refreshStopList();
    }


    /**
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
     */
    @FXML
    public void updateStop() throws SQLException {
        StopAdmin selectedStop = tableViewStops.getSelectionModel().getSelectedItem();
        if (selectedStop != null) {
            String name = txtfld_name.getText();
            selectedStop.setName(name);
            stopDAO.update(selectedStop);
            clearInputFields();
            refreshStopList();
        }
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
     */
    @FXML
    public void deleteStop() throws SQLException {
        StopAdmin selectedStop = tableViewStops.getSelectionModel().getSelectedItem();
        if (selectedStop == null) {
            System.out.println("No se ha seleccionado ninguna parada.");
            return;
        }

        stopDAO.delete(selectedStop);
        stopList.remove(selectedStop); // Eliminar la parada de la lista de observables

        // Actualizar el TableView con la lista modificada
        tableViewStops.refresh();
    }

    /**
     * @author Adrián Ruiz Sánchez
     */
    @FXML
    private void searchIdStop() {
        String searchId = txtfld_searchId.getText();
        if (!searchId.isEmpty()) {
            try {
                int id = Integer.parseInt(searchId);
                StopAdmin stop = stopDAO.findById(id);
                if (stop != null) {
                    clearInputFields();
                } else {
                    // Mostrar un mensaje indicando que no se encontró ninguna parada con el ID proporcionado
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Manejar el error al convertir el ID de búsqueda a un número entero
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar el error al buscar una parada por ID
            }
        } else {
            // Mostrar un mensaje de error indicando que se debe ingresar un ID de parada válido para buscar
        }
    }

    /**
     * @author Adrián Ruiz Sánchez
     */
    private void clearInputFields() {

        txtfld_name.clear();
    }

}

