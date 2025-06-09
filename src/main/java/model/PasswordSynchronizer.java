package model;


import java.util.List;

public class PasswordSynchronizer {
    private PasswordDAO source;
    private PasswordDAO target;

    public PasswordSynchronizer(PasswordDAO source, PasswordDAO target) {
        this.source = source;
        this.target = target;
    }

    public void sync() {
        // Получить все пароли из ресурса
        List<Password> sourcePasswords = source.getAllPasswords();

        // Очистить target и всё из этого ресурса
        sourcePasswords.forEach(password -> {
            // Проверить существуют ли они в target
            boolean exists = target.getAllPasswords().stream()
                    .anyMatch(p -> p.getId() == password.getId());

            if (exists) {
                target.updatePassword(password);
            } else {
                target.addPassword(password);
            }
        });

        // Убрать несуществующие пароли из target
        target.getAllPasswords().forEach(targetPassword -> {
            boolean existsInSource = sourcePasswords.stream()
                    .anyMatch(p -> p.getId() == targetPassword.getId());

            if (!existsInSource) {
                target.deletePassword(targetPassword.getId());
            }
        });
    }
}