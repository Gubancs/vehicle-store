import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
}

group = "hu.gubancs"
version = "1.0-0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.cache:cache-api:1.1.1")
    implementation("com.hazelcast:hazelcast:5.4.0")
    implementation("com.hazelcast:hazelcast-hibernate53:5.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-serialization")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    //implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
