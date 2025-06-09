package model;

public class PasswordSt {

    private int id; // Уникальный идентификатор пароля
    private String login; // Название (имя сайта или сервиса)
    private String password; // Хранимый пароль

    // Конструктор класса
    public PasswordSt(int id, String name, String password, String notes, int securityLevel) {
        this.id = id;
        this.login = name;
        this.password = password;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return login;}
    public void setName(String name) {this.login = name;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}


    // Переопределение toString для удобного отображения информации о пароле
//    @Override
//    public String toString() {
//        return "Password{" +
//                "id=" + id +
//                ", name='" + login + '\'' +
//                ", password='" + password +
//                '}';
//    }


}


