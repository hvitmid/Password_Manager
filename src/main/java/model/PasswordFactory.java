package model;

public class PasswordFactory {
    public static final String MEMORY = "memory";
    public static final String FILE = "file";
    public static final String DATABASE = "database";

    public static PasswordDAO createPasswordDAO(String type) {
        switch (type.toLowerCase()) {
            case MEMORY:
                return new RAMPasswordDAO();
            case FILE:
                return new FilePasswordDAO("passwords.txt");
            case DATABASE:
                return new DBPasswordDAO();
            default:
                throw new IllegalArgumentException("Unknown DAO type: " + type);
        }
    }
}