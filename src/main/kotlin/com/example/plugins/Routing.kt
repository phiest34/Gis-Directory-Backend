package com.example.plugins

import com.example.models.ErrorStructure
import com.example.register.WebMapInfoStaticStorage
import com.example.register.webMaps
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting(
    webMapInfoStorage: WebMapInfoStaticStorage
) {
    routing {
        staticResources("/", basePackage = "static/")

        get("/") {
            call.respondRedirect("/openapi")
        }

        get("/webmaps") {
            call.respond(mapOf("webmaps" to webMaps))
        }

        get("webmaps/{assetName}") {
            val assetName = call.parameters["assetName"]

            if (assetName == null) {
                call.respondText(
                    "Asset name required as path parameter.",
                    contentType = ContentType.Text.Plain,
                    HttpStatusCode.BadRequest
                )
                return@get
            }
            val result =
                webMaps.find { it.assetPath == assetName }?.let { mapOf("webmap" to it) }
            if (result == null) {
                call.respondText(
                    "No result found.",
                    contentType = ContentType.Text.Plain,
                    HttpStatusCode.NoContent
                )
                return@get
            }
            call.respond(result)
        }

        get("webmap_info/{assetName}") {
            val assetName = call.parameters["assetName"]
                ?: return@get call.respondText(
                    "Asset name required as path parameter.",
                    contentType = ContentType.Text.Plain,
                    HttpStatusCode.BadRequest
                )

            val result = webMapInfoStorage[assetName] ?: return@get call.respondText(
                "No result found.",
                contentType = ContentType.Text.Plain,
                HttpStatusCode.NoContent
            )
            call.respond(result)
        }
    }
}
