module com.example.muehle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.muehle to javafx.fxml;
    exports com.muehle;
}