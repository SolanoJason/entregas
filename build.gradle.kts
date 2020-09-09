plugins {
    java
    application
    id("com.github.ben-manes.versions") version "0.31.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

// Aca indicamos la ubicación del main
application {
    applicationName = "Proyecto en aula Programación 3"
    mainClassName = "ventanas.Facultad"
}

group = "com.grupouno"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Esto es para unit tests, aun no se va a usar
    testImplementation("junit", "junit", "4.13")

    // logger, esto es super super util (frontend)
    implementation("com.google.flogger", "flogger", "0.5.1")
    //backend logger
    implementation("com.google.flogger", "flogger-log4j2-backend", "0.5.1")
    implementation("org.apache.logging.log4j", "log4j-core", "2.13.3")

    //Para el skin, hay un montón de skins para escoger
    // https://github.com/JFormDesigner/FlatLaf/tree/master/flatlaf-intellij-themes#themes
    implementation("com.formdev", "flatlaf", "0.41")
    implementation("com.formdev", "flatlaf-intellij-themes", "0.41")

    //Conector para mysql
    implementation("mysql", "mysql-connector-java", "8.0.21")
}
