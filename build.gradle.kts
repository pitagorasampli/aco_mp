import java.io.FileInputStream
import java.util.Properties

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

val keystorePropertiesFile = rootProject.file("azure-config.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))


repositories {
    google()
    mavenCentral()
    maven {
        name = "ACO-KMP"
        url = uri(keystoreProperties.getProperty("repositoryUrl"))
        credentials {
            username = keystoreProperties.getProperty("userName")
            password = keystoreProperties.getProperty("azureMavenAccessToken")
        }
    }
}