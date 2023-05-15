package com.example.plugins

import com.example.data.WebMapRepository
import com.example.models.WebMap
import com.example.models.WebMapInfo
import com.example.register.WebMapInfoStaticStorage
import io.github.smiley4.ktorswaggerui.dsl.OpenApiResponse
import io.ktor.http.*
import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting(
    webMapInfoStorage: WebMapInfoStaticStorage
) {
    routing {

        get("/") {
            call.respondRedirect("/openapi")
        }

        get("/{asset_map}", {
            description = "Загружает карту в зависимости от URL параметра {asset_map}"
        }) {
            val assetMap = call.parameters["asset_map"]
            call.respondText(File("src/main/resources/static/$assetMap/index.html").readText(), ContentType.Text.Html)
        }

        get("/images/{image_file}", {
            description = "Загрузить изображение с выбранной локацией"
            response {
                addResponse(OpenApiResponse("200"))
            }
        }) {
            val image = call.parameters["image_file"]
            val file = File("src/main/resources/static/images/$image")
            if (file.exists()) {
                call.respondFile(File("src/main/resources/static/images/$image"))
            } else {
                call.respond(HttpStatusCode.NoContent, "No image found by such link")
            }

        }

        get("/{asset_map}/{...}", {
            hidden = true
        }) {
            val path = call.request.path()

            val file = File("src/main/resources/static/$path")

            val imageContentType = when (file.extension) {
                "png" -> ContentType.Image.PNG
                "jpeg", "jpg" -> ContentType.Image.JPEG
                else -> null
            }
            imageContentType?.let {
                call.respondFile(file)
                return@get
            }
            val contentType = when (file.extension) {
                "css" -> ContentType.Text.CSS
                "js" -> ContentType.Text.JavaScript
                "html" -> ContentType.Text.Html
                else -> ContentType.Text.Plain
            }
            call.respondText(File("src/main/resources/static/$path").readText(), contentType)
        }


        get("/webmaps", {
            description = "Загрузить список всех доступных карт"
            response {
                HttpStatusCode.OK to {
                    body<List<WebMap>>()
                }
            }
        }) {
            val result = WebMapRepository.getWebMaps()
            call.respond(mapOf("webmaps" to result))
        }

        get("webmaps/{assetName}", {
            response {
                HttpStatusCode.OK to {
                    body<WebMap>()
                }
            }
        }) {
            val assetName = call.parameters["assetName"]

            if (assetName == null) {
                call.respondText(
                    "Asset name required as path parameter.",
                    contentType = ContentType.Text.Plain,
                    HttpStatusCode.BadRequest
                )
                return@get
            }
            val webMaps = WebMapRepository.getWebMaps()
            val result = webMaps.find { it.assetPath == assetName }?.let { mapOf("webmap" to it) }
            if (result == null) {
                call.respondText(
                    "No result found.", contentType = ContentType.Text.Plain, HttpStatusCode.NoContent
                )
                return@get
            }
            call.respond(result)
        }

        get("webmap_info/{assetName}", {
            description = "Загрузить доп информацию о карте"
            response {
                HttpStatusCode.OK to {
                    body<WebMapInfo>()
                }
            }
        }) {
            val assetName = call.parameters["assetName"] ?: return@get call.respondText(
                "Asset name required as path parameter.",
                contentType = ContentType.Text.Plain,
                HttpStatusCode.BadRequest
            )

            val result = WebMapRepository.getWebMapInfo(assetName, webMapInfoStorage) ?: return@get call.respondText(
                "No result found.", contentType = ContentType.Text.Plain, HttpStatusCode.NoContent
            )
            call.respond(result)
        }
    }
}
