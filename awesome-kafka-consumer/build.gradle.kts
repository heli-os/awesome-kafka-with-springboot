apply(from = "../docker-build.gradle")

dependencies {
    implementation(project(":commons:model"))
    implementation(project(":commons:common-util"))
    implementation(project(":commons:persistence-database"))
    implementation(project(":commons:health-check"))
    implementation(project(":commons:logback-appender"))
    implementation(project(":commons:awesome-kafka"))

    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}
