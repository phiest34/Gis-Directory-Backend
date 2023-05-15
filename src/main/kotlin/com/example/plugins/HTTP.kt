package com.example.plugins

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.*

fun Application.configureSwagger() {
    install(SwaggerUI) {
        swagger {
            swaggerUrl = "openapi"
            forwardRoot = true
        }
        info {
            title = "GIS Directory API"
            version = "latest"
            description = "API for GIS Directory android app and desktop admin panel."
        }
        server {
            description = "Development Server"
        }

    }

}
