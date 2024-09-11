package br.com.ampli.aco

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform