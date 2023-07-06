module com.example.calendar2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calendar2 to javafx.fxml;
    exports com.example.calendar2;
}