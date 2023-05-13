package com.example.admin

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class CreateMapJson @OptIn(ExperimentalSerializationApi::class) constructor(
    val name: String,
    val key: String,
    val description: String,
    @JsonNames("large_description")
    val largeDescription: String,
)