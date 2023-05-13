package com.example.firebase

import com.example.models.WebMap
import com.example.models.WebMapInfo
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames


@Serializable
data class FirebaseModel(
    val description: String,
    val key: String,
    val imageUrl: String,
    val name: String,
    @JsonNames("large_description")
    val largeDescription: String
) {
    fun toWebMap(): WebMap = WebMap(key, name, description)

    fun toWebMapInfo(): WebMapInfo = WebMapInfo(key, imageUrl, largeDescription)

    fun toMap(): Map<String, String> = mapOf(
        "description" to description,
        "key" to key,
        "imageUrl" to imageUrl,
        "name" to name,
        "largeDescription" to largeDescription
    )

    companion object {
        fun fromMap(map: Map<String, String>): FirebaseModel? {
            val description = map["description"] ?: return null
            val key = map["key"] ?: return null
            val imageUrl = map["imageUrl"] ?: return null
            val name = map["name"] ?: return null
            val largeDescription = map["largeDescription"] ?: return null
            return FirebaseModel(description, key, imageUrl, name, largeDescription)
        }
    }
}
