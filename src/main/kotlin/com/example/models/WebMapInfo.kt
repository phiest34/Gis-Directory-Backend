package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class WebMapInfo(
    val assetPath: String,
    val imageUrl: String,
    val description: String
)
