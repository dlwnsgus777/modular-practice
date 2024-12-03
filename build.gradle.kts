import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

plugins {
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"

    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"
    kotlin("plugin.jpa") version "1.9.10"
    kotlin("kapt") version "1.9.10"

    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

repositories {
    mavenCentral()
}

// root를 포함한 전체 모듈
allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        reporters {
            reporter(ReporterType.JSON)
        }
    }

    tasks.withType<GenerateReportsTask> {
        reportsOutputDirectory.set(
            rootProject.layout.buildDirectory.dir("reports/ktlint/${project.name}")
        )
    }
}

// root를 제외한 나머지 모듈
subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    group = "com.modular"
    version = "0.0.1-SNAPSHOT"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        // spring
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-security")

        // kotlin
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // swagger
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springdocOpenapiStarterWebmvcUiVersion")}")

        // spring actuator
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        // hibernate-spatial
        implementation("org.hibernate:hibernate-spatial:${property("hibernateSpatialVersion")}")


        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("io.rest-assured:rest-assured")
        testImplementation("io.mockk:mockk")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.rest-assured:rest-assured")
    }

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.Embeddable")
        annotation("jakarta.persistence.MappedSuperclass")
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}