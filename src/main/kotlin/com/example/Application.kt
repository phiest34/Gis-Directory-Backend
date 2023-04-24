package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.register.registerWebMaps

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        module()
    }.start(wait = true)

}

fun Application.module() {
    registerWebMaps()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
