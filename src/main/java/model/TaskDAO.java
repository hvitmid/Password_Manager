package model;

import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasks();
    Task getTaskById(int id);
    void addTask(Task task);
    void updateTask(Task task);  // Параметр должен быть model.Task, а не javafx.concurrent.Task
    void deleteTask(int id);
}