package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePasswordDAO implements PasswordDAO {
    private String filename;
    private List<Password> passwords;

    public FilePasswordDAO(String filename) {
        this.filename = filename;
        this.passwords = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    passwords.add(new Password(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2]
                    ));
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Password p : passwords) {
                writer.write(p.getId() + "," + p.getName() + "," + p.getPassword() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Password> getAllPasswords() {
        return new ArrayList<>(passwords);
    }

    @Override
    public void addPassword(Password password) {
        int newId = passwords.stream().mapToInt(Password::getId).max().orElse(0) + 1;
        password.setId(newId);
        passwords.add(password);
        saveToFile();
    }

    @Override
    public void updatePassword(Password password) {
        for (int i = 0; i < passwords.size(); i++) {
            if (passwords.get(i).getId() == password.getId()) {
                passwords.set(i, password);
                saveToFile();
                break;
            }
        }
    }

    @Override
    public void deletePassword(int id) {
        passwords.removeIf(p -> p.getId() == id);
        saveToFile();
    }

    @Override
    public String getType() {
        return "FILE";
    }
}