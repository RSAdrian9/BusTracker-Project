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

public class StopsByLineController {
    @FXML
    private ListView<Stop> stopListView;
    @FXML
    private Button closeButton;

    private Connection con;
    private StopDAO stopDAO;
    private Line selectedLine;

    public void setConnection(Connection con) {

        this.con = con;
    }

    public void setStopDAO(StopDAO stopDAO) {

        this.stopDAO = stopDAO;
    }

    public void setSelectedLine(Line selectedLine) {
        this.selectedLine = selectedLine;
        initializeListView();
    }

    @FXML
    private void initializeListView() {
        if (selectedLine != null) {
            try {
                List<Stop> stops = stopDAO.findStopsByLine(selectedLine.getId_bus());
                ObservableList<Stop> stopList = FXCollections.observableArrayList(stops);
                stopListView.setItems(stopList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void closeView() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}





