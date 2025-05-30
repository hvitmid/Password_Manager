module com.example.password_manager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.password_manager to javafx.fxml;
    exports com.example.password_manager;
}