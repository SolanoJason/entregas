plugins {
	java
	application
	id("com.github.ben-manes.versions") version "0.33.0"
	id("com.github.johnrengelman.shadow") version "6.0.0"
	id("de.inetsoftware.setupbuilder") version "4.8.7"
	id("com.github.decyg.innosetup") version "1.2"
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.compileJava {
	options.isIncremental = true
	options.isWarnings = true
	options.isFailOnError = true
}

// Aca indicamos la ubicación del main
application {
	applicationName = "Proyecto en aula Programación 3"
	mainClassName = "ventanas.Menu"
}

group = "com.grupodos"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
	maven {
		url = uri("https://oss.sonatype.org/content/repositories/comgithublgooddatepicker-1026")
	}
	jcenter()
	maven {
		url = uri("http://jaspersoft.jfrog.io/jaspersoft/third-party-ce-artifacts/")
	}
}


dependencies {
	// Esto es para unit tests, aun no se va a usar
	testImplementation("junit", "junit", "4.13")

	// logger, esto es super super util (frontend)
	implementation("com.google.flogger", "flogger", "0.5.1")
	//backend logger
	implementation("com.google.flogger", "flogger-log4j2-backend", "0.5.1")
	implementation("org.apache.logging.log4j", "log4j-core", "2.13.3")

	//Para el skin, hay un montÃ³n de skins para escoger
	// https://github.com/JFormDesigner/FlatLaf/tree/master/flatlaf-intellij-themes#themes
	implementation("com.formdev", "flatlaf", "0.42")
	implementation("com.formdev", "flatlaf-intellij-themes", "0.42")
	implementation("com.formdev", "flatlaf-extras", "0.42")
	implementation("com.formdev", "svgSalamander", "1.1.2.3")

	// Para usar calendario
	implementation("com.github.lgooddatepicker", "LGoodDatePicker", "11.0.2")


	//Hace posible (y no llorar sangre) escribir interfaces graficas a mano
	implementation("com.miglayout", "miglayout-core", "5.2")
	implementation("com.miglayout", "miglayout-swing", "5.2")

	//Conector para mysql
	implementation("mysql", "mysql-connector-java", "8.0.21")

	//Guava
	//Porque guava?
	// https://github.com/google/guava/wiki
	implementation("com.google.guava", "guava", "29.0-jre")

	// Para cargar la libreria de Jasper
	implementation("net.sf.jasperreports", "jasperreports", "6.14.0")
}

setupBuilder {
	vendor = "Grupo 1"
	application = "G1P3"
	appIdentifier = "ProjectoP3"
	description = "La aplicacion resultante del projecto de Programacion 3 - Ciclo 2020-1"
//	icons = "test-icon.icns"
	defaultResourceLanguage = "es"

	from("jar.outputs")

//	bundleJre = "C:\\Program Files\\AdoptOpenJDK\\jdk-11.0.8.10-hotspot"
	mainClass = "ventanas.Menu"
	mainJar = "Programa1-1.0-SNAPSHOT-all.jar"

}