module com.example.cpu_scheduling {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.cpu_scheduling to javafx.fxml;
    exports com.example.cpu_scheduling;
}