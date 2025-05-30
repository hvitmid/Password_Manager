package model;

import java.util.List;

public interface PasswordDAO {
    List<Password> getAllPasswords();
    void addPassword(Password password);
    void updatePassword(Password password);
    void deletePassword(int id);
    String getType();
}