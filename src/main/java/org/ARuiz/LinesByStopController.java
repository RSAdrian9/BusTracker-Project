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

public class LinesByStopController {
    @FXML
    private ListView<Line> lineListView;
    @FXML
    private Button closeButton;

    private Connection con;
    private LineDAO lineDAO;
    private Stop selectedStop;



    public void setConnection(Connection con) {
        this.con = con;
    }

    public void setLineDAO(LineDAO lineDAO) {
        this.lineDAO = lineDAO;
    }

    public void setSelectedStop(Stop selectedStop) {
        this.selectedStop = selectedStop;
        initializeListView();
    }


    @FXML
    private void initializeListView() {
        if (selectedStop != null) {
            try {
                List<Line> lines = lineDAO.findLinesByStop(selectedStop.getId_stop());
                ObservableList<Line> lineList = FXCollections.observableArrayList(lines);
                lineListView.setItems(lineList);
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

