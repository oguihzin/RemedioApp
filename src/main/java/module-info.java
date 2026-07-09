module com.example.remedioapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.remedioapp to javafx.fxml;
    exports com.example.remedioapp;
}