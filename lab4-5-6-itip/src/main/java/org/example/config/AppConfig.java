package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "org.example")
public class AppConfig {
    // Здесь можно добавить дополнительные бины при необходимости

    // Пример дополнительной конфигурации (опционально):
    /*
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    */
}