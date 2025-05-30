package com.example.password_manager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Password;
import model.PasswordDAO;
import model.PasswordFactory;
import model.PasswordSynchronizer;

public class HelloController {
    @FXML private TableView<Password> passwordTable;
    @FXML private TableColumn<Password, String> nameColumn;
    @FXML private TableColumn<Password, String> passwordColumn;
    @FXML private TextField nameField;
    @FXML private TextField passwordField;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private ChoiceBox<String> daoTypeChoiceBox;

    private PasswordDAO passwordDAO;

    @FXML
    public void initialize() {
        // Setup DAO type selection
        daoTypeChoiceBox.getItems().addAll(
                PasswordFactory.MEMORY,
                PasswordFactory.FILE,
                PasswordFactory.DATABASE
        );
        daoTypeChoiceBox.setValue(PasswordFactory.MEMORY);
        daoTypeChoiceBox.setOnAction(e -> switchDAO());

        switchDAO();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        refreshTable();

        passwordTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        nameField.setText(newSelection.getName());
                        passwordField.setText(newSelection.getPassword());
                    }
                });
    }

    private void switchDAO() {
        String daoType = daoTypeChoiceBox.getValue();
        passwordDAO = PasswordFactory.createPasswordDAO(daoType);
        refreshTable();
    }

    @FXML
    private void handleAddPassword() {
        if (isInputValid()) {
            Password password = new Password(0, nameField.getText(), passwordField.getText());
            passwordDAO.addPassword(password);
            refreshTable();
            clearFields();
        }
    }

    @FXML
    private void handleUpdatePassword() {
        Password selected = passwordTable.getSelectionModel().getSelectedItem();
        if (selected != null && isInputValid()) {
            selected.setName(nameField.getText());
            selected.setPassword(passwordField.getText());
            passwordDAO.updatePassword(selected);
            refreshTable();
        } else {
            showAlert("Ошибка", "Не выбрано", "Пожалуйста выберите пароль для обновления");
        }
    }

    @FXML
    private void handleDeletePassword() {
        Password selected = passwordTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            passwordDAO.deletePassword(selected.getId());
            refreshTable();
            clearFields();
        } else {
            showAlert("Ошибка", "Не выбрано", "Пожалуйста выберите пароль для удаления");
        }
    }

    @FXML
    private void handleSync() {
        PasswordDAO memoryDao = PasswordFactory.createPasswordDAO(PasswordFactory.MEMORY);
        PasswordDAO fileDao = PasswordFactory.createPasswordDAO(PasswordFactory.FILE);

        new PasswordSynchronizer(memoryDao, fileDao).sync();
        new PasswordSynchronizer(fileDao, memoryDao).sync();

        refreshTable();
    }

    private void refreshTable() {
        passwordTable.getItems().setAll(passwordDAO.getAllPasswords());
    }

    private boolean isInputValid() {
        if (nameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showAlert("Ошибка", "Пустые поля", "Пожалуйста выберите все поля");
            return false;
        }
        return true;
    }

    private void clearFields() {
        nameField.clear();
        passwordField.clear();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}