package model;

import java.util.List;

public interface PasswordDAO {


    List<PasswordSt> getAllTasks();
    PasswordSt getPasswordById(int id);

}

//
//List<Task> getAllTasks();
//Task getTaskById(int id);
//void addTask(Task task);
//void updateTask(Task task);
//void deleteTask(int id);
//
