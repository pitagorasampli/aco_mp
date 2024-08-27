package br.com.ampli.aco_multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform