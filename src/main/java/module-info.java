module com.example.password_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;


    opens com.example.password_manager to javafx.fxml;
    exports com.example.password_manager;
}