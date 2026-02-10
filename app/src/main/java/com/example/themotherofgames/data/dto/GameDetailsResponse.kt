package com.example.themotherofgames.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsResponse(
    val id: Int,
    val name: String,
    @SerialName("name_original")
    val nameOriginal: String? = null,
    val description: String? = null,
    @SerialName("description_raw")
    val descriptionRaw: String? = null,
    val released: String? = null,
    @SerialName("background_image")
    val backgroundImage: String? = null,
    @SerialName("background_image_additional")
    val backgroundImageAdditional: String? = null,
    val rating: Double? = null,
    val playtime: Int? = null,
    val genres: List<GenreDto>? = null
)