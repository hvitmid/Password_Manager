package model;

public class Task {
    private int id;
    private String name;
    private String time;
    private String context;
    private int status;

    public Task(int id, String name, String time, String context, int status) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.context = context;
        this.status = status;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}