# Менеджер паролей

JavaFX приложение для управления паролями с поддержкой нескольких хранилищ.

## Архитектура

Приложение использует паттерн DAO (Data Access Object):

- **Модель**: Класс `Password`
- **Интерфейс DAO**: `PasswordDAO`
- **Реализации DAO**:
  - `RAMPasswordDAO` - хранение в оперативной памяти
  - `FilePasswordDAO` - хранение в файле
  - `DBPasswordDAO` - хранение в SQLite базе данных
- **Фабрика**: `PasswordFactory` создает экземпляры DAO
- **Синхронизация**: `PasswordSynchronizer` для синхронизации между хранилищами

## Функционал

- Добавление, изменение и удаление паролей
- Переключение между типами хранилищ
- Синхронизация данных между разными хранилищами
- Юнит-тесты с mock-объектами
