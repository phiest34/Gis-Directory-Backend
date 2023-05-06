package com.example.models

import kotlinx.serialization.Serializable


@Serializable
data class ErrorStructure(
    val error: String,
    val code: Int
)
