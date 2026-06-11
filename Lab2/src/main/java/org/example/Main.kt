package org.example

import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

import java.util.Properties

fun main() {
    val logger = LoggerFactory.getLogger("org.example.Main")

    logger.info("=== Начало работы программы ===")

    val properties = Properties()
    try {
        val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream("build-passport.properties")
        if (inputStream != null) {
            properties.load(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            logger.info("Информация о сборке: {}", properties.getProperty("build.message"))
        }
    } catch (e: Exception) {
        logger.warn("Не удалось загрузить build-passport.properties: ${e.message}")
    }

    println("Введите строку для обработки:")
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val input = reader.readLine()

    if (input != null) {
        val reversed = StringUtils.reverse(input)
        val capitalized = StringUtils.capitalize(input)

        logger.info("Введенная строка: {}", input)
        logger.info("Перевернутая строка: {}", reversed)
        logger.info("Капитализированная строка: {}", capitalized)

        println("Результат (reverse): $reversed")
        println("Результат (capitalize): $capitalized")
    }

    logger.info("=== Завершение работы программы ===")
}