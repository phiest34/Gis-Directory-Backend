package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class WebMap(
    val assetPath: String,
    val mapName: String,
    val description: String,
)

