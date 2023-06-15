module org.ARuiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;
    requires javafx.graphics;

    opens org.ARuiz to javafx.fxml;
    opens org.ARuiz.Model.Connections to java.xml.bind;
    exports org.ARuiz;
    opens org.ARuiz.Model.Domain to javafx.base;
    exports  org.ARuiz.Model.Domain;
    exports org.ARuiz.Util;


}
