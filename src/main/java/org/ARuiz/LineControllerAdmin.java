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
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.Stop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * La clase `LineControllerAdmin` es un controlador para la vista de administración de líneas.
 * Utiliza JavaFX para la construcción de la interfaz gráfica.
 *
 * Esta clase proporciona métodos para inicializar la vista, manejar eventos de botones y realizar operaciones relacionadas con las líneas, como agregar, actualizar y eliminar líneas, así como buscar líneas por su identificador.
 * @author Adrián Ruiz
 */
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
    private TextField txtfld_searchId;
    @FXML
    private TextField txtfld_name;
    @FXML
    private TextField txtfld_place;
    @FXML
    private Button btnDelete;

    private ErrorMessageController errorController;
    private Connection con;
    private LineDAO lineDAO;
    private StopDAO stopDAO;

    /**
     * Establece la conexión a la base de datos.
     *
     * @param con La conexión a establecer.
     * @author Adrián Ruiz
     */
    public void setConnection(Connection con) {
        this.con = con;
        lineDAO = new LineDAO();
    }

    private ObservableList<Line> lineList;

    /**
     * Inicializa el controlador y la vista.
     * Se llama automáticamente cuando se carga la interfaz gráfica.
     *
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @author Adrián Ruiz
     */
    public void initialize() throws SQLException {
        errorController = new ErrorMessageController();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_bus"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));

        tableLineView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updateDeleteButtonState();
        });

        lineList = FXCollections.observableArrayList();
        tableLineView.setItems(lineList);

        refreshLineList();
    }

    /**
     * Actualiza la lista de líneas.
     * @author Adrián Ruiz
     */
    public void refreshLineList() {
        if (lineDAO == null) {
            lineDAO = new LineDAO();
        }
        try {
            List<Line> lines = lineDAO.findAll();
            lineList.setAll(lines);

        } catch (SQLException e) {
            String errorMessage = "Error al refrescar" + e.getMessage();
            errorController.showErrorView(errorMessage);
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista de inicio.
     *
     * @param event El evento de acción que desencadenó el método.
     * @author Adrián Ruiz
     */
    @FXML
    public void showHome(ActionEvent event) {
        try {
            // Cargar la vista LineViewAdmin desde su archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
            Parent lineViewAdmin = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(lineViewAdmin);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            String errorMessage = "Error al mostrar inicio" + e.getMessage();
            errorController.showErrorView(errorMessage);
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la vista de líneas.
     *
     * @param event El evento de acción que desencadenó el método.
     * @author Adrián Ruiz
     */
    @FXML
    public void refreshLineView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LineViewAdmin.fxml"));
            Parent lineViewAdmin = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(lineViewAdmin);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            String errorMessage = "Error al refrescar" + e.getMessage();
            errorController.showErrorView(errorMessage);
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el estado del botón de eliminar.
     * El botón de eliminar se habilita o deshabilita según si se ha seleccionado una línea en la tabla.
     * @author Adrián Ruiz
     */
    @FXML
    private void updateDeleteButtonState() {
        Line selectedLine = tableLineView.getSelectionModel().getSelectedItem();
        boolean lineSelected = (selectedLine != null);
        btnDelete.setDisable(!lineSelected);
    }

    /**
     * Agrega una nueva línea.
     * Obtiene los datos de la interfaz gráfica y los utiliza para crear y agregar una nueva línea en la base de datos.
     * @throws SQLException Si ocurre un error durante la comunicación con la base de datos.
     * @author Adrián Ruiz
     */
    @FXML
    public void addLine() {
        try {
            String name = txtfld_name.getText();
            Integer place = Integer.valueOf(txtfld_place.getText());

            List<Line> lines = lineDAO.findAll();
            int lastId = lines.isEmpty() ? 0 : lines.get(lines.size() - 1).getId_bus();
            Line newLine = new Line(lastId + 1, name, place);
            lineDAO.insert(newLine);

            clearInputFields();
            refreshLineList();
        } catch (SQLException e) {
            String errorMessage = "Inserta una línea válida" + e.getMessage();
            errorController.showErrorView(errorMessage);
            e.printStackTrace();
        }
    }

    /**
     * Actualiza una línea existente.
     * Obtiene los datos de la interfaz gráfica y actualiza la línea seleccionada en la base de datos.
     * @throws SQLException Si ocurre un error durante la comunicación con la base de datos.
     * @author Adrián Ruiz
     */
    @FXML
    public void updateLine() {
        try {
            Line selectedLine = tableLineView.getSelectionModel().getSelectedItem();
            if (selectedLine != null) {
                String name = txtfld_name.getText();
                String place = txtfld_place.getText();

                selectedLine.setName(name);
                selectedLine.setPlace(Integer.parseInt(place));

                lineDAO.update(selectedLine);

                clearInputFields();
                refreshLineList();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una línea.
     * Elimina la línea seleccionada de la base de datos y actualiza la lista de líneas.
     * @throws SQLException Si ocurre un error durante la comunicación con la base de datos.
     * @author Adrián Ruiz
     */
    @FXML
    public void deleteLine() {
        try {
            Line selectedLine = tableLineView.getSelectionModel().getSelectedItem();
            if (selectedLine == null) {
                System.out.println("No se ha seleccionado ninguna parada.");
                return;
            }

            lineDAO.delete(selectedLine);
            lineList.remove(selectedLine);
            tableLineView.refresh();

        } catch (SQLException e) {
            String errorMessage = "No se ha podido borrar la parada seleccionada." + e.getMessage();
            errorController.showErrorView(errorMessage);
            e.printStackTrace();
        }
    }

    /**
     * Muestra la información de las paradas por línea.
     * Muestra la vista `ShowStopsByLine.fxml` con las paradas asociadas a la línea seleccionada.
     * @author Adrián Ruiz
     */
    @FXML
    public void infoStopsByLine() {
        Line selectedLine = tableLineView.getSelectionModel().getSelectedItem();
        if (selectedLine != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowStopsByLine.fxml"));
                Parent root = loader.load();

                StopsByLineController controller = loader.getController();
                controller.setConnection(con);
                controller.setStopDAO(new StopDAO(con));
                controller.setSelectedLine(selectedLine);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                String errorMessage = "Selecciona una línea para consultar" + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();
            }
        }
    }

    /**
     * Agrega una parada a la línea.
     * Muestra la vista `AddStopsByLine.fxml` para seleccionar y agregar paradas a la línea seleccionada.
     * @author Adrián Ruiz
     */
    @FXML
    public void addStopToLine() {
        Line selectedLine = tableLineView.getSelectionModel().getSelectedItem();
        if (selectedLine != null) {
            try {
                // Abrir la vista para seleccionar paradas
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStopsByLine.fxml"));
                Parent selectStopView = loader.load();
                AddStopsByLineController addStopsController = loader.getController();

                // Pasar la línea seleccionada al controlador de la vista de agregar paradas
                addStopsController.setLine(selectedLine);

                Stage stage = new Stage();
                stage.setTitle("Seleccionar paradas");
                stage.setScene(new Scene(selectStopView));
                stage.show();

            } catch (IOException e) {
                String errorMessage = "Añade correctamente una parada" + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();
            }
        }
    }

    /**
     * Busca una línea por su identificador.
     * Obtiene el identificador de la interfaz gráfica y busca la línea correspondiente en la base de datos.
     * Si se encuentra la línea, se actualizan los campos de texto y se selecciona la línea en la tabla.
     * @author Adrián Ruiz
     */
    @FXML
    private void searchIdLine() {
        String searchId = txtfld_searchId.getText();
        if (!searchId.isEmpty()) {
            try {
                int id = Integer.parseInt(searchId);
                Line line = lineDAO.findById(id);
                if (line != null) {
                    txtfld_name.setText(line.getName());
                    txtfld_place.setText(String.valueOf(line.getPlace()));

                    tableLineView.getSelectionModel().select(line);
                    updateDeleteButtonState();
                } else {
                    System.out.println("No se encontró ninguna línea con el ID proporcionado");
                }
            } catch (NumberFormatException e) {
                String errorMessage = "Error al buscar" + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();

            } catch (SQLException e) {
                String errorMessage = "Error en la base de datos: " + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();
            }
        } else {
            System.out.println("Se debe ingresar un ID de línea válido para buscar");
        }
    }

    private void clearInputFields() {
        txtfld_name.clear();
        txtfld_place.clear();
    }

}


