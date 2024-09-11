import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}


kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_18)
        }

        publishLibraryVariants("release", "debug")
    }

    val xcframeworkName = "aco"
    val xcf = XCFramework(xcframeworkName)


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = xcframeworkName

            // Specify CFBundleIdentifier to uniquely identify the framework
            binaryOption("bundleId", "br.com.ampli.${xcframeworkName}")
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.material3.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

val keystorePropertiesFile = rootProject.file("azure-config.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

val githubKeystorePropertiesFile = rootProject.file("github-config.properties")
val githubKeystoreProperties = Properties()
githubKeystoreProperties.load(FileInputStream(githubKeystorePropertiesFile))


publishing {
    publications {
        withType<MavenPublication> {
            groupId = "br.com.ampli"
            artifactId = "complementary_activities_multiplatform"
            version = "0.0.12"
        }
    }
    repositories {
        maven {
            name = "ACO-KMP"
            url = uri(keystoreProperties.getProperty("repositoryUrl"))
            credentials {
                username = keystoreProperties.getProperty("userName")
                password = keystoreProperties.getProperty("azureMavenAccessToken")
            }
        }

        maven {
            name = "GitHubPackages"

            url = uri("https://maven.pkg.github.com/pitagorasampli/aco_mp")

            credentials {
                 username =
                    githubKeystoreProperties.getProperty("gpr.usr") ?: System.getenv("GPR_USER")
                password =
                    githubKeystoreProperties.getProperty("gpr.key") ?: System.getenv("GPR_API_KEY")
            }
        }
    }


}