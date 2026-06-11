package org.example.util;

import org.example.model.dto.UserDto;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataGenerator {

    private final UserService userService;
    private final Random random = new Random();

    private static final String[] FIRST_NAMES = {
        "Александр", "Максим", "Артем", "Дмитрий", "Никита",
        "Анна", "Мария", "Елена", "Ольга", "Наталья"
    };

    private static final String[] LAST_NAMES = {
        "Иванов", "Петров", "Сидоров", "Козлов", "Новиков",
        "Морозов", "Волков", "Соколов", "Лебедев", "Кузнецов"
    };

    private static final String[] DOMAINS = {
        "gmail.com", "yandex.ru", "mail.ru", "yahoo.com", "outlook.com"
    };

    public DataGenerator(UserService userService) {
        this.userService = userService;
    }

    public UserDto generateRandomUser() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String fullName = firstName + " " + lastName;

        String email = generateRandomEmail(firstName, lastName);
        String phone = generateRandomPhone();

        UserDto userDto = new UserDto();
        userDto.setName(fullName);
        userDto.setEmail(email);
        userDto.setPhone(phone);
        userDto.setDeviceToken(generateRandomToken());
        userDto.setTelegramChatId(generateRandomTelegramId());

        return userDto;
    }

    public void addRandomUsers(int count) {
        for (int i = 0; i < count; i++) {
            UserDto userDto = generateRandomUser();
            userService.createUser(userDto);
            System.out.println("Добавлен пользователь: " + userDto.getName() + " (" + userDto.getEmail() + ")");
        }
    }

    private String generateRandomEmail(String firstName, String lastName) {
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        int number = random.nextInt(1000);
        return transliterate(firstName.toLowerCase()) + "." +
               transliterate(lastName.toLowerCase()) + number + "@" + domain;
    }

    private String generateRandomPhone() {
        return "+7" + String.format("%03d%03d%02d%02d",
            900 + random.nextInt(100),
            random.nextInt(1000),
            random.nextInt(100),
            random.nextInt(100));
    }

    private String generateRandomToken() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
        }
        return token.toString();
    }

    private String generateRandomTelegramId() {
        return "@" + transliterate(FIRST_NAMES[random.nextInt(FIRST_NAMES.length)].toLowerCase()) +
               "_" + random.nextInt(1000);
    }

    private String transliterate(String text) {
        return text.replace("а", "a").replace("б", "b").replace("в", "v")
                   .replace("г", "g").replace("д", "d").replace("е", "e")
                   .replace("ё", "e").replace("ж", "zh").replace("з", "z")
                   .replace("и", "i").replace("й", "y").replace("к", "k")
                   .replace("л", "l").replace("м", "m").replace("н", "n")
                   .replace("о", "o").replace("п", "p").replace("р", "r")
                   .replace("с", "s").replace("т", "t").replace("у", "u")
                   .replace("ф", "f").replace("х", "h").replace("ц", "c")
                   .replace("ч", "ch").replace("ш", "sh").replace("щ", "sh")
                   .replace("ъ", "").replace("ы", "y").replace("ь", "")
                   .replace("э", "e").replace("ю", "yu").replace("я", "ya");
    }
}