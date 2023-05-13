package com.example.admin

import com.example.firebase.FirebaseModel
import com.example.firebase.WebmapsFireBaseStorage
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.io.File


private const val ADMIN_URL = "admin/"
fun Application.adminRouting() {
    routing {
        post("$ADMIN_URL/add") {
            val multipartData = call.receiveMultipart()
            var mapModel: CreateMapJson? = null
            var image: File? = null
            var mapFile: File? = null
            var imageUrl = ""
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        if (part.name == "json") {
                            mapModel = try {
                                Json.decodeFromString(CreateMapJson.serializer(), part.value)
                            } catch (e: Exception) {
                                null
                            }
                        }
                    }

                    is PartData.FileItem -> {
                        if (part.name == "image") {
                            val fileName = part.originalFileName as String
                            val fileBytes = part.streamProvider().readBytes()
                            File(RAW).mkdir()
                            val path = "$RAW$fileName"
                            image = File(path).apply { writeBytes(fileBytes) }
                        }
                        if (part.name == "map") {
                            val fileName = part.originalFileName as String
                            val fileBytes = part.streamProvider().readBytes()
                            val path = "$RAW$fileName"
                            val createFile = File(path)
                            createFile.writeBytes(fileBytes)
                            mapFile = createFile
                        }
                    }

                    else -> {}
                }
                part.dispose()
            }

            if (mapFile == null || image == null || mapModel == null) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    "Error getting json value",
                )
                return@post
            }

            val mapModelNotNull = mapModel!!
            val imageNonNull = image ?: return@post
            val key = mapModelNotNull.key
            val imagePath = "$IMAGE_DIR$key.${imageNonNull.extension}"
            File(imagePath).writeBytes(imageNonNull.readBytes())
            imageUrl = "$IMAGE_URL_BASE$key.${imageNonNull.extension}"

            val isExtractedSuccessFully = extractZip(mapFile!!, mapModelNotNull.key)

            if (isExtractedSuccessFully) {
                val resultModel = FirebaseModel(
                    mapModelNotNull.description,
                    mapModelNotNull.key,
                    imageUrl,
                    mapModelNotNull.name,
                    mapModelNotNull.largeDescription
                )
                val isSuccess = WebmapsFireBaseStorage.putWebMapAsync(resultModel)
                if (isSuccess) {
                    call.respond(HttpStatusCode.OK, "File successfully loaded")
                } else {
                    call.respondText(status = HttpStatusCode.InternalServerError) { "Error while loading file" }
                }

            }
        }
    }
}

private fun extractZip(file: File, key: String): Boolean {
    val packageName = file.nameWithoutExtension
    UnzipUtils.unzip(file, RAW)
    val unZipPackage = File(RAW + packageName).listFiles()?.firstOrNull() ?: return false
    htmlFix(unZipPackage, key)
    jsFix(unZipPackage)
    return unZipPackage.renameTo(File("src/main/resources/static/$key"))
}

private fun htmlFix(webMapDir: File, key: String) {
    val indexHtml = webMapDir.listFiles()?.find { it.extension == "html" }
    val tag = """<base href=./$key/ />"""

    val initialFile = mutableListOf<String>()
    indexHtml?.bufferedReader().use {
        it?.forEachLine(initialFile::add)
    }

    indexHtml?.bufferedWriter().use { out ->
        repeat(initialFile.size) { index ->
            if (index == 3) {
                out?.write(tag)
                out?.newLine()
            }
            out?.write(initialFile[index])
            out?.newLine()
        }
    }
}

private fun jsFix(webMapDir: File) {
    val jsHash = File(webMapDir.path + "/js/leaflet-hash.js")
    val initialFile = mutableListOf<String>()
    jsHash.bufferedReader().use {
        it.forEachLine(initialFile::add)
    }

    jsHash.bufferedWriter().use {
        repeat(initialFile.size) { index ->
            if (index !in 88..91) {
                it.write(initialFile[index])
                it.newLine()
            }
        }
    }
}

private const val IMAGE_DIR = "src/main/resources/static/images/"
private const val IMAGE_URL_BASE = "https://gis-directory.herokuapp.com/images/"
private const val RAW = "src/main/resources/uploaded_raw/"
