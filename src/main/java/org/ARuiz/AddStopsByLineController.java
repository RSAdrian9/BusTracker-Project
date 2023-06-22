package org.ARuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.ARuiz.Model.DAO.LineDAO;
import org.ARuiz.Model.DAO.StopDAO;
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.Stop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la vista de agregar paradas a una línea.
 * @author Adrián Ruiz
 */
public class AddStopsByLineController {
    @FXML
    private ChoiceBox<String> choiceBoxStops;

    @FXML
    private Button addStop;
    private ErrorMessageController errorController;
    private StopDAO stopDAO;
    private LineDAO lineDAO;
    private List<Stop> addedStops;
    private Line selectedLine;
    private Connection con;
    private List<Stop> stops;
    private List<String> stopnames;


    /**
     * Establece la línea seleccionada para agregar paradas.
     *
     * @param line La línea seleccionada.
     * @author Adrián Ruiz
     */
    public void setLine(Line line) {
        selectedLine = line;
    }

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
     * Constructor de la clase AddStopsByLineController.
     * @author Adrián Ruiz
     */
    public AddStopsByLineController(){

    }


    /**
     * Inicializa el controlador.
     *
     * @throws SQLException Si ocurre algún error al obtener la lista de nombres de paradas.
     * @author Adrián Ruiz
     */
    public void initialize() throws SQLException {
        errorController = new ErrorMessageController();

        // Inicializar StopDAO y obtener la lista de nombres de paradas
        stopDAO = new StopDAO(); // Asegúrate de proporcionar la conexión a la base de datos en el constructor si es necesario
        this.stops= stopDAO.getAllStopNames();
        this.stopnames=new ArrayList<>();
        for(Stop s:this.stops){
            this.stopnames.add(s.getName());
        }
        // Agregar los nombres de paradas al ChoiceBox
        choiceBoxStops.getItems().addAll(this.stopnames);

        // Inicializar la lista de paradas agregadas
        addedStops = new ArrayList<>();
    }

    /**
     * Maneja el evento de agregar una parada a la línea.
     * @author Adrián Ruiz
     */
    @FXML
    private void addStopToLine() {
        String selectedStop = choiceBoxStops.getValue();
        if (selectedStop != null && selectedLine != null) {
            try {
                int idstop=-1;
                for(int i=0;i<this.stops.size()&&idstop==-1;i++){
                    if(this.stops.get(i).getName().equals(selectedStop)){
                        idstop=this.stops.get(i).getId_stop();
                    }
                }
                this.lineDAO = new LineDAO();
                this.lineDAO.addStopToLine(selectedLine.getId_bus(),idstop);



                // Aquí puedes realizar cualquier otra acción después de agregar la parada a la línea

                // Cerrar la ventana
                Stage stage = (Stage) choiceBoxStops.getScene().getWindow();
                stage.close();

            } catch (SQLException e) {
                String errorMessage = "Añade correctamente las paradas a línea" + e.getMessage();
                errorController.showErrorView(errorMessage);
                e.printStackTrace();
            }
        }
    }

}

