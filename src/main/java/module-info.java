module org.ARuiz {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.ARuiz to javafx.fxml;
    exports org.ARuiz;
    exports org.ARuiz.Controller;
    opens org.ARuiz.Controller to javafx.fxml;
}
