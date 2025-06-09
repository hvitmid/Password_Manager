package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Password {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty password = new SimpleStringProperty();

    public Password(int id, String name, String password) {
        this.id.set(id);
        this.name.set(name);
        this.password.set(password);
    }

    // Геттеры для свойств
    public SimpleIntegerProperty idProperty() { return id; }
    public SimpleStringProperty nameProperty() { return name; }
    public SimpleStringProperty passwordProperty() { return password; }

    // Обычные геттеры и сеттеры
    public int getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getPassword() { return password.get(); }

    public void setId(int id) { this.id.set(id); }
    public void setName(String name) { this.name.set(name); }
    public void setPassword(String password) { this.password.set(password); }
}