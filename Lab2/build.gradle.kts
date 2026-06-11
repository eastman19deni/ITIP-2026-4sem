import java.util.Properties
import java.time.LocalDateTime
import java.io.Writer

plugins {
    kotlin("jvm") version "2.0.0"
    id("application")
    id("com.gradleup.shadow") version "9.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Стандартная библиотека Kotlin
    implementation(kotlin("stdlib"))

    // Apache Commons Lang3 (проверьте актуальную версию на mvnrepository.com)
    implementation("org.apache.commons:commons-lang3:3.12.0")

    // Логирование (SLF4J + Logback)
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.11")

    // Тесты
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("org.example.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

// Конфигурация для Shadow Jar (Задание 4)
tasks.shadowJar {
    manifest {
        attributes(Pair("Main-Class", "org.example.Main"))
    }
    archiveClassifier.set("all")
}

// Задача для вывода информации (из примера в методичке)
abstract class PrintInfoTask : DefaultTask() {
    @TaskAction
    fun print() {
        println("======================================")
        println("Это моя первая пользовательская задача!")
        println("Проект: ${project.name}")
        println("Версия Gradle: ${project.gradle.gradleVersion}")
        println("======================================")
    }
}

tasks.register<PrintInfoTask>("printInfo") {
    group = "Custom"
    description = "Выводит информацию о проекте"
}

// Задача генерации паспорта сборки (Задание 5)
tasks.register("generateBuildPassport") {
    group = "Custom"
    description = "Генерирует файл build-passport.properties"

    val outputDir = layout.buildDirectory.dir("resources/main")
    val outputFile = outputDir.map { it.file("build-passport.properties") }

    outputs.file(outputFile)

    doLast {
        outputDir.get().asFile.mkdirs()
        val props = Properties()

        // Переменные окружения
        props["build.user"] = System.getenv("USERNAME") ?: System.getenv("USER") ?: "unknown"
        props["build.os"] = System.getProperty("os.name")
        props["build.java.version"] = System.getProperty("java.version")
        props["build.date"] = LocalDateTime.now().toString()
        props["build.message"] = "Приветственное сообщение от Gradle!"

        outputFile.get().asFile.writer().use { writer: Writer ->
            props.store(writer, "Build Passport")
        }
        println("Файл build-passport.properties создан!")
    }
}

// Интеграция: задача processResources зависит от generateBuildPassport
tasks.named("processResources") {
    dependsOn(tasks.named("generateBuildPassport"))
}


// --- ЗАДАНИЕ 7: Git Hash и Инкремент Версии ---

// Функция для получения хеша коммита
fun getGitHash(): String {
    return try {
        val process = Runtime.getRuntime().exec("git rev-parse --short HEAD")
        process.inputStream.bufferedReader().readText().trim()
    } catch (e: Exception) {
        "no-git"
    }
}

// Функция для чтения и инкремента версии
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

// Обновляем версию проекта динамически
version = "${incrementVersion()}-SNAPSHOT"

// Модифицируем задачу generateBuildPassport
tasks.named("generateBuildPassport") {
    doLast {
        val outputFile = layout.buildDirectory.dir("resources/main").map { it.file("build-passport.properties") }
        val props = Properties()

        props["build.user"] = System.getenv("USERNAME") ?: "unknown"

        // Новые свойства из Задания 7
        props["build.git.hash"] = getGitHash()
        props["build.version"] = version.toString()

        outputFile.get().asFile.writer().use { writer: Writer ->
            props.store(writer, "Build Passport")
        }
    }
}