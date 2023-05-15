package com.example

import com.example.admin.adminRouting
import com.example.firebase.initFirebase
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.register.WebMapInfoStaticStorage
import com.example.register.registerWebMaps

fun main() {
    startServer {
        module()
    }

}

fun Application.module() {
    registerWebMaps()
    initFirebase()
    configureSwagger()
    configureRouting(WebMapInfoStaticStorage())
    adminRouting()
}

private fun startServer(registerBlock: Application.() -> Unit) {
    val port = try {
        System.getenv("PORT").toInt()
    } catch (e: Exception) {
        null
    }

    port?.let {
        embeddedServer(Netty, port = port) {
            registerBlock()
        }.start(wait = true)
    } ?: run {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = registerBlock)
            .start(wait = true)
    }
}
