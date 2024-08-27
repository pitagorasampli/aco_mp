import java.io.FileInputStream
import java.net.URI
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
}

val keystorePropertiesFile = rootProject.file("azure-config.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))


kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "18"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "complementary_activities_multiplatform"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "br.com.ampli.complementary_activities_multiplatform"
    compileSdk = 34
    defaultConfig {
        minSdk = 23
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            groupId = "br.com.ampli.complementary_activities_multiplatform"
            artifactId = "complementary_activities_multiplatform"
            version = "0.0.1"
        }
    }
    repositories {
        maven {
            name = "ACO-Multiplatform-Test"
            url = URI(keystoreProperties.getProperty("repositoryUrl"))
            credentials {
                username = keystoreProperties.getProperty("userName")
                password = keystoreProperties.getProperty("azureMavenAccessToken")
            }
        }
    }
}