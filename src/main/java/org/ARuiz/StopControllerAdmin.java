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
import org.ARuiz.Model.Domain.Stop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la vista de administración de paradas.
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

    private Connection con;
    StopDAO stopDAO;

    /**
     * Establece la conexión a la base de datos.
     *
     * @param con La conexión a establecer.
     */
    public void setConnection(Connection con) {
        this.con = con;
        stopDAO = new StopDAO(con);
    }

    private ObservableList<Stop> stopList;

    public void initialize() throws SQLException {
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
     * Actualiza la lista de paradas.
     */
    public void refreshStopList() {
        if (stopDAO == null) {
            stopDAO = new StopDAO(con);
        }
        try {
            List<Stop> stops = stopDAO.findAll();
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

    @FXML
    private void updateDeleteButtonState() {
        Stop selectedStop = tableViewStops.getSelectionModel().getSelectedItem();
        boolean lineSelected = (selectedStop != null);
        btnDelete.setDisable(!lineSelected);
    }


    /**
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
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
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
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
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
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
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Se debe ingresar un ID de parada válido para buscar");
        }
    }


    /**
     * @author Adrián Ruiz Sánchez
     */
    private void clearInputFields() {
        txtfld_name.clear();
    }

}

