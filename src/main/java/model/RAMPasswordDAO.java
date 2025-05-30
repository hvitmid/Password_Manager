package model;

import java.util.ArrayList;
import java.util.List;

public class RAMPasswordDAO implements PasswordDAO {
    private List<Password> passwords = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Password> getAllPasswords() {
        return new ArrayList<>(passwords);
    }

    @Override
    public void addPassword(Password password) {
        password.setId(nextId++);
        passwords.add(password);
    }

    @Override
    public void updatePassword(Password password) {
        for (int i = 0; i < passwords.size(); i++) {
            if (passwords.get(i).getId() == password.getId()) {
                passwords.set(i, password);
                break;
            }
        }
    }

    @Override
    public void deletePassword(int id) {
        passwords.removeIf(p -> p.getId() == id);
    }

    @Override
    public String getType() {
        return "MEMORY";
    }
}