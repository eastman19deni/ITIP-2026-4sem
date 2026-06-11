# 📄 Полный отчет по лабораторной работе Lab2

## 1. Общая информация о проекте

| Параметр | Значение |
|----------|----------|
| **Название проекта** | GradleLab |
| **Версия** | 12-SNAPSHOT (автоматически инкрементируется) |
| **Group** | org.example |
| **Язык** | Kotlin 2.0.0 |
| **JVM Target** | Java 17 |
| **Gradle** | 9.2.0 |
| **Основной класс** | `org.example.MainKt` |

---

## 2. Структура проекта

```
Lab2/
├── build.gradle.kts          # Конфигурация сборки
├── settings.gradle.kts       # Настройки проекта
├── version.txt               # Файл версии (сейчас: 12)
├── gradlew                   # Gradle wrapper (Unix)
├── gradlew.bat              # Gradle wrapper (Windows)
├── gradle/wrapper/
│   └── gradle-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   └── Main.kt       # Основной код
│   │   └── resources/        # Ресурсы
│   └── test/
│       ├── java/             # Тесты
│       └── resources/        # Тестовые ресурсы
└── build/                    # Выходная директория
```

---

## 3. Зависимости проекта

| Зависимость | Версия | Назначение |
|-------------|--------|------------|
| `kotlin-stdlib` | 2.0.0 | Стандартная библиотека Kotlin |
| `commons-lang3` | 3.12.0 | Утилиты для работы со строками |
| `slf4j-api` | 2.0.9 | API логирования |
| `logback-classic` | 1.4.11 | Реализация логирования |
| `junit-jupiter` | 5.9.1 | Фреймворк для тестов |

---

## 4. Функциональность приложения

Программа выполняет следующие действия:

1. Загружает файл `build-passport.properties` из ресурсов
2. Запрашивает у пользователя ввод строки
3. Обрабатывает строку с помощью Apache Commons Lang3:
   - **reverse** — переворачивает строку
   - **capitalize** — капитализирует первую букву
4. Выводит результаты и логирует действия

---

## 5. Gradle задачи

### Стандартные задачи

| Задача | Описание |
|--------|----------|
| `./gradlew build` | Сборка проекта |
| `./gradlew run` | Запуск приложения |
| `./gradlew test` | Запуск тестов |
| `./gradlew clean` | Очистка build-директории |

### Пользовательские задачи

| Задача | Описание |
|--------|----------|
| `./gradlew printInfo` | Выводит информацию о проекте |
| `./gradlew generateBuildPassport` | Генерирует файл `build-passport.properties` |
| `./gradlew shadowJar` | Создает fat-JAR со всеми зависимостями |

---

## 6. Как правильно запустить файл

### 🔹 Способ 1: Запуск через Gradle (рекомендуется)

```bash
# Перейдите в директорию проекта
cd /Users/deni/work/Lab2

# Запустите приложение
./gradlew run
```

**Что происходит:**

1. Gradle компилирует Kotlin-код
2. Генерирует файл `build-passport.properties` (задача `generateBuildPassport`)
3. Запускает главный класс `org.example.MainKt`
4. Программа запросит ввод строки

---

### 🔹 Способ 2: Запуск через JAR-файл

```bash
# 1. Соберите fat-JAR со всеми зависимостями
./gradlew shadowJar

# 2. Запустите созданный JAR-файл
java -jar build/libs/GradleLab-12-SNAPSHOT-all.jar
```

---

### 🔹 Способ 3: Запуск после полной сборки

```bash
# 1. Выполните полную сборку
./gradlew build

# 2. Запустите приложение
./gradlew run
```

---

## 7. Пример работы программы

```
$ ./gradlew run

> Task :run
Введите строку для обработки:
Hello World
Результат (reverse): dlroW olleH
Результат (capitalize): Hello world

BUILD SUCCESSFUL in 2s
```

---

## 8. Особенности реализации

### Автоматическая инкрементация версии

При каждой сборке версия увеличивается на 1 (читается из `version.txt`)

### Git Hash

В `build-passport.properties` добавляется хеш текущего коммита Git

### Build Passport

Файл содержит:

- `build.user` — имя пользователя
- `build.os` — операционная система
- `build.java.version` — версия Java
- `build.date` — дата сборки
- `build.git.hash` — хеш коммита
- `build.version` — версия проекта

---

## 9. Команды для проверки работы

```bash
# Проверка сборки
./gradlew build

# Запуск приложения
./gradlew run

# Просмотр информации о проекте
./gradlew printInfo

# Запуск тестов
./gradlew test

# Очистка и пересборка
./gradlew clean build
```

---

## 10. Требования для запуска

| Требование | Статус |
|------------|--------|
| Java 17+ | ✅ Установлено (OpenJDK 17.0.16) |
| Gradle Wrapper | ✅ Присутствует |
| Git (опционально) | ⚠️ Для получения git hash |

---

## 11. Анализ кода (Main.kt)

### Импорты

```kotlin
import org.apache.commons.lang3.StringUtils  // Утилиты для строк
import org.slf4j.LoggerFactory               // Логирование
import java.io.BufferedReader                // Ввод с консоли
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Properties                  // Чтение properties-файлов
```

### Основная логика

1. **Инициализация логгера** — для записи событий в лог
2. **Загрузка build-passport.properties** — чтение метаданных сборки
3. **Чтение ввода пользователя** — через BufferedReader
4. **Обработка строки**:
   - `StringUtils.reverse(input)` — переворот строки
   - `StringUtils.capitalize(input)` — заглавная первая буква
5. **Вывод результатов** — в консоль и в лог

---

## 12. Анализ build.gradle.kts

### Плагины

```kotlin
kotlin("jvm") version "2.0.0"        // Kotlin для JVM
id("application")                     // Приложение с main-классом
id("com.gradleup.shadow") version "9.2.0"  // Fat-JAR сборка
```

### Конфигурация application

```kotlin
application {
    mainClass.set("org.example.MainKt")
}
```

### Пользовательская задача PrintInfoTask

Выводит информацию о проекте:
- Имя проекта
- Версию Gradle

### Задача generateBuildPassport

Генерирует файл с метаданными сборки:
- Пользователь
- ОС
- Версия Java
- Дата
- Git hash
- Версия проекта

### Инкремент версии (Задание 7)

```kotlin
fun incrementVersion(): String {
    val versionFile = file("version.txt")
    var version = 1
    if (versionFile.exists()) {
        version = versionFile.readText().trim().toIntOrNull() ?: 1
        version++
    }
    versionFile.writeText(version.toString())
    return version.toString()
}
```

---

## 📌 Краткая инструкция по запуску

```bash
# Самый простой способ:
cd /Users/deni/work/Lab2
./gradlew run
```

Введите строку когда программа запросит — получите результат обработки!

---

## 13. Выполненные задания

| Задание | Статус | Описание |
|---------|--------|----------|
| Задание 1 | ✅ | Базовая настройка Kotlin + Gradle |
| Задание 2 | ✅ | Подключение зависимостей (Commons Lang3, Logback) |
| Задание 3 | ✅ | Настройка логирования |
| Задание 4 | ✅ | Shadow Jar для fat-JAR сборки |
| Задание 5 | ✅ | Генерация build-passport.properties |
| Задание 6 | ⚠️ | Многомодульность (закомментировано) |
| Задание 7 | ✅ | Git Hash + Инкремент версии |

---

*Отчет сгенерирован: 2026-03-26*
