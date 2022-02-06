module abdel.uiversion {
    requires javafx.controls;
    requires javafx.fxml;


    opens abdel.uiversion to javafx.fxml;
    exports abdel.uiversion;
}