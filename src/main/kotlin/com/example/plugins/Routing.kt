package com.example.plugins

import com.example.register.webMaps
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        staticResources("/", basePackage = "static")

        get("/webmaps") {
            call.respond("webmaps" to webMaps)
        }
    }
}