plugins {
    id("org.springframework.boot") version "3.0.0-M3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.gatling.gradle") version "3.8.1"
    java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/milestone")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

tasks.test {
    useJUnitPlatform()
}
