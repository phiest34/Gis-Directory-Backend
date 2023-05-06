package com.example.plugins

import com.example.models.ErrorStructure
import com.example.register.webMaps
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        staticResources("/", basePackage = "static/")

        get("/") {
            call.respondRedirect("/openapi")
        }

        get("/webmaps") {
            call.respond("webmaps" to webMaps)
        }

        get("webmaps/{assetName}") {
            val assetName = call.parameters["assetName"] ?: return@get
            val respond =
                webMaps.find { it.assetPath == assetName }?.let { "webmap" to it } ?: ErrorStructure(
                    "No element find",
                    204
                )

            call.respond(respond)
        }
    }
}
