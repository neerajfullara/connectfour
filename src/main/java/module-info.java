module com.project.connectfour {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.project.connectfour to javafx.fxml;
    exports com.project.connectfour;

}