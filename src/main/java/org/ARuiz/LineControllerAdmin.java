package org.ARuiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.ARuiz.Model.DAO.LineDAO;
import org.ARuiz.Model.DAO.StopDAO;
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.StopAdmin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class LineControllerAdmin {
    @FXML
    private TableView<Line> tableLineView;
    @FXML
    private TableColumn<Line, Integer> idColumn;
    @FXML
    private TableColumn<Line, String> nameColumn;
    @FXML
    private TableColumn<Line, Integer> placeColumn;
    @FXML
    private TableColumn<Line, String> routeColumn;
    @FXML
    private TableColumn<Line, LocalTime> timeTableColumn;
    private TextField txtfld_name;
    private TextField txtfld_searchId;

    @FXML
    private Label lbl_idAdmin;


    private Connection con;
    LineDAO lineDAO;

    /**
     * Establece la conexión a la base de datos.
     *
     * @param con La conexión a establecer.
     */
    public void setConnection(Connection con) {
        this.con = con;
        lineDAO = new LineDAO();
    }

    private ObservableList<Line> lineList;

    public void initialize() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_bus"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("line_name"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("timetable"));

        lineList = FXCollections.observableArrayList();
        tableLineView.setItems(lineList);

        refreshLineList();
    }

    /**
     * Actualiza la lista de paradas.
     */
    public void refreshLineList() {
        if (lineDAO == null) {
            lineDAO = new LineDAO(); // Crear una nueva instancia de LineDAO
        }
        try {
            List<Line> lines = lineDAO.findAll();
            lineList.setAll(lines);
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de la excepción
        }
    }

    @FXML
    public void showHome(ActionEvent event) {
        try {
            // Cargar la vista LineViewAdmin desde su archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
            Parent lineViewAdmin = loader.load();

            // Obtener el Stage actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Crear una nueva escena con la vista LineViewAdmin
            Scene scene = new Scene(lineViewAdmin);

            // Establecer la nueva escena en el Stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier excepción que pueda ocurrir durante la carga de la vista
        }
    }

    @FXML
    public void refreshLineView(ActionEvent event) {
        try {
            // Cargar la vista LineViewAdmin desde su archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LineViewAdmin.fxml"));
            Parent lineViewAdmin = loader.load();

            // Obtener el Stage actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Crear una nueva escena con la vista LineViewAdmin
            Scene scene = new Scene(lineViewAdmin);

            // Establecer la nueva escena en el Stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier excepción que pueda ocurrir durante la carga de la vista
        }
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
     */
    @FXML
    public void addLine() throws SQLException {
        String name = txtfld_name.getText();
        Line newLine = new Line();
        lineDAO.insert(newLine);
        clearInputFields();
        refreshLineList();
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
     */
    @FXML
    public void updateLine() throws SQLException {
        Line selectedLine = tableLineView.getSelectionModel().getSelectedItem();
        if (selectedLine != null) {
            String name = txtfld_name.getText();
            selectedLine.setLine_name(name);
            lineDAO.update(selectedLine);
            clearInputFields();
            refreshLineList();
        }
    }

    /**
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
     */
    @FXML
    public void deleteLine() throws SQLException {
        Line selectedLine = tableLineView.getSelectionModel().getSelectedItem();
        if (selectedLine == null) {
            System.out.println("No se ha seleccionado ninguna parada.");
            return;
        }

        lineDAO.delete(selectedLine);
        lineList.remove(selectedLine); // Eliminar la parada de la lista de observables

        // Actualizar el TableView con la lista modificada
        tableLineView.refresh();
    }

    /**
     * @author Adrián Ruiz Sánchez
     */
    @FXML
    private void searchIdLine() {
        String searchId = txtfld_searchId.getText();
        if (!searchId.isEmpty()) {
            try {
                int id = Integer.parseInt(searchId);
                Line stop = lineDAO.findById(id);
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
        txtfld_searchId.clear();
    }
}


