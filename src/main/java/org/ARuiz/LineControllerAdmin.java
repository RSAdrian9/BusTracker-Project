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
import org.ARuiz.Model.Domain.Line;

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
    @FXML
    private TextField txtfld_searchId;
    @FXML
    private TextField txtfld_name;
    @FXML
    private TextField txtfld_place;
    @FXML
    private TextField txtfld_route;
    @FXML
    private TextField txtfld_timetable;
    @FXML
    private Button btnDelete;


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

        tableLineView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updateDeleteButtonState();
        });

        lineList = FXCollections.observableArrayList();
        tableLineView.setItems(lineList);

        refreshLineList();
    }

    /**
     * Actualiza la lista de paradas.
     */
    public void refreshLineList() {
        if (lineDAO == null) {
            lineDAO = new LineDAO();
        }
        try {
            List<Line> lines = lineDAO.findAll();
            lineList.setAll(lines);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

    @FXML
    private void updateDeleteButtonState() {
        Line selectedLine = tableLineView.getSelectionModel().getSelectedItem();
        boolean lineSelected = (selectedLine != null);
        btnDelete.setDisable(!lineSelected);
    }


    /**
     * @author Adrián Ruiz Sánchez
     * @throws SQLException
     */
    @FXML
    public void addLine() throws SQLException {
        String name = txtfld_name.getText();
        Integer place = Integer.valueOf(txtfld_place.getText());
        String route = txtfld_route.getText();
        LocalTime timetable = LocalTime.parse(txtfld_timetable.getText());

        List<Line> lines = lineDAO.findAll();
        int lastId = lines.isEmpty() ? 0 : lines.get(lines.size() - 1).getId_bus();
        Line newLine = new Line(lastId + 1, name, place, route, timetable);
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
            String place = txtfld_place.getText();
            String route = txtfld_route.getText();
            String timetable = txtfld_timetable.getText();

            selectedLine.setLine_name(name);
            selectedLine.setPlace(Integer.parseInt(place));
            selectedLine.setRoute(route);
            selectedLine.setTimetable(LocalTime.parse(timetable));

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
        lineList.remove(selectedLine);
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
                Line line = lineDAO.findById(id);
                if (line != null) {
                    txtfld_name.setText(line.getLine_name());
                    txtfld_place.setText(String.valueOf(line.getPlace()));
                    txtfld_route.setText(line.getRoute());
                    txtfld_timetable.setText(String.valueOf(line.getTimetable()));

                    tableLineView.getSelectionModel().select(line);
                    updateDeleteButtonState();
                } else {
                    System.out.println("No se encontró ninguna línea con el ID proporcionado");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Se debe ingresar un ID de línea válido para buscar");
        }
    }


    /**
     * @author Adrián Ruiz Sánchez
     */
    private void clearInputFields() {
        txtfld_name.clear();
        txtfld_place.clear();
        txtfld_route.clear();
        txtfld_timetable.clear();
    }
}


