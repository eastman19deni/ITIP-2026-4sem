package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("приложение запущено!");

        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = new User("Daniyal", 20);
            String json = mapper.writeValueAsString(user);
            logger.info("объект в JSON: {}", json);
        } catch (Exception e) {
            logger.error("ошибка сериализации", e);
        }
    }
}

class User {
    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
