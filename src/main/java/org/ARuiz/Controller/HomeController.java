package org.ARuiz.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import org.ARuiz.App;

public class HomeController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
    }
}