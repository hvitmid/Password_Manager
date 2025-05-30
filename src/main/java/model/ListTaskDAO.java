package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ListTaskDAO implements TaskDAO {

    private List<Task> tasks;

    public ListTaskDAO(int size) {
        tasks = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Task task = new Task(
                    i + 1,
                    "Task " + (i + 1),
                    randomTime(random),
                    "Context for task " + (i + 1),
                    random.nextInt(2)
            );
            tasks.add(task);
        }
    }


    public List<Task> getAllTasks() {
        return tasks;
    }


    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }


    public void addTask(Task task) {
        tasks.add(task);
    }


    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                return;
            }
        }
    }


    public void deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return;
            }
        }
    }





}
