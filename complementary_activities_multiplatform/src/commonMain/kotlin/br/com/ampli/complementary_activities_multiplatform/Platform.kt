package br.com.ampli.complementary_activities_multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform