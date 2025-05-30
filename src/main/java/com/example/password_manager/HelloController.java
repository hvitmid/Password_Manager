package com.example.password_manager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.FileTaskDAO;
import model.PasswordDAO;

import java.util.List;

public class HelloController {

    @FXML
    private ListView<String> taskListView; // Для отображения задач

    @FXML
    private Button loadTasksButton; // Кнопка для загрузки задач
    private PasswordDAO passwordDAO; // Объект для работы с задачами



    @FXML
    private void loadTasks(MouseEvent event) {
        // Инициализация DAO для загрузки задач из файла
        passwordDAO = new FileTaskDAO("passwords.txt");

        // Получаем все задачи и заполняем ListView
        List<Task> tasks = passwordDAO.getAllTasks();
        taskListView.getItems().clear(); // Очищаем предыдущие элементы

        for (Task task : tasks) {
            // Добавляем каждую задачу в ListView в формате "ID - Название - Время - Статус"
            taskListView.getItems().add(
                    task.getId() + " - " + task.getName() + " - " + task.getTime() + " - " + task.getStatus()
            );
        }
    }



}
