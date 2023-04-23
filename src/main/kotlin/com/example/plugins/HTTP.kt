package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*

fun Application.configureHTTP() {
    routing {
        swaggerUI("openapi", swaggerFile = "openapi/documentation.yaml")
    }
}
