rootProject.name = "modular-practice"

include("app")
include("auth")
include("common")

include(
    "member",
    "member:member-api",
    "member:member-domain",
    "member:member-infra",
)

include(
    "product",
    "product:product-api",
    "product:product-domain",
    "product:product-infra",
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("org.springframework.boot") version "3.3.4"
        id("io.spring.dependency-management") version "1.1.6"

        val kotlinVersion = "1.9.25"
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("kapt") version kotlinVersion

        id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    }
}