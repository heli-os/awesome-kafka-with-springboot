pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val palantirDockerVersion: String by settings

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        id("com.palantir.docker") version palantirDockerVersion

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
    }
}

rootProject.name = "awesome-kafka-with-springboot"
include("commons")
include("commons:model")
include("commons:common-util")
include("commons:health-check")
include("commons:persistence-database")
include("commons:logback-appender")
include("commons:consumer-logger")

include("commons:awesome-kafka")
include("awesome-kafka-consumer")
