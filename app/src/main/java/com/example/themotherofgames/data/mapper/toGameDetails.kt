package com.example.themotherofgames.data.mapper

import com.example.themotherofgames.data.dto.GameDetailsResponse
import com.example.themotherofgames.domain.model.GameDetails

fun GameDetailsResponse.toGameDetails(): GameDetails {
    return GameDetails(
        id = id,
        name = name,
        description = description ?: descriptionRaw ?: "No description available",
        released = released ?: "Unknown",
        rating = rating ?: 0.0,
        playtime = playtime ?: 0,
        backgroundImage = backgroundImage ?: "",
        backgroundImageAdditional = backgroundImageAdditional,
        genres = genres?.map { it.name } ?: emptyList()
    )
}