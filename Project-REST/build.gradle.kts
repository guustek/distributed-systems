plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "rsi.project.rest"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springframework.boot:spring-boot-starter-web")
 	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("com.google.code.gson:gson:2.10.1")
	implementation("com.itextpdf:itext-core:8.0.4")

}
