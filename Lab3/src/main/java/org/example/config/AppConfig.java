package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example") // Сканирует все подпакеты org.example
public class AppConfig {
    // Пусто, все бины создаются через @Service
}