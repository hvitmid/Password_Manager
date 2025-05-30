package model;

public class PasswordFactory {

    public static String BD="база данных";

    public static String FILE="файл";

    public static String RAM="временно";



    public static PasswordDAO createTaskDAO(String type) {

        if (type.equalsIgnoreCase(FILE)) {

            return new FilePasswordDAO("passwords.txt");//имя файла

        } else if (type.equalsIgnoreCase(RAM)) {

            return new ListTask(10);//генератор на 10 задач

        } else {

            throw new IllegalArgumentException("Invalid datasource type!");

        }
    }
}
