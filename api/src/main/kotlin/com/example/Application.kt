package com.example

import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
}

ktor {
    deployment {
        port = 8080
        port = ${?PORT}
        host = "0.0.0.0"
    }
}