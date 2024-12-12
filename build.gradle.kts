
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    kotlin("plugin.serialization") version "2.0.20"
}

group = "id.deval"
version = "0.0.2"

application {
//    mainClass.set("id.deval.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
//    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

//    SERVER
    implementation(libs.server.ktor.netty)

//    LOGGING
    implementation(libs.ktor.server.logging)

//    STATUS
    implementation(libs.ktor.server.status)

//    SERIALIZATION
    implementation(libs.ktor.serialization)
    implementation(libs.kotlinx.serialzation)

//    ContentNegotiation
    implementation(libs.ktor.content.negotiation)

//    TEMPLATE
    implementation(libs.ktor.html.builder)
}
