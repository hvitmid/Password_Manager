package model.mocks;

import model.Password;
import model.PasswordDAO;

import java.util.ArrayList;
import java.util.List;

public class MockPasswordDAO implements PasswordDAO {
    private List<Password> passwords = new ArrayList<>();
    public boolean addCalled = false;
    public boolean updateCalled = false;
    public boolean deleteCalled = false;

    @Override
    public List<Password> getAllPasswords() {
        return new ArrayList<>(passwords);
    }

    @Override
    public void addPassword(Password password) {
        addCalled = true;
        passwords.add(password);
    }

    @Override
    public void updatePassword(Password password) {
        updateCalled = true;
        for (int i = 0; i < passwords.size(); i++) {
            if (passwords.get(i).getId() == password.getId()) {
                passwords.set(i, password);
                break;
            }
        }
    }

    @Override
    public void deletePassword(int id) {
        deleteCalled = true;
        passwords.removeIf(p -> p.getId() == id);
    }

    @Override
    public String getType() {
        return "MOCK";
    }
}