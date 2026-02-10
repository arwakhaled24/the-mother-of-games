package com.example.themotherofgames.domain.repositories

import com.example.themotherofgames.domain.model.GameDetails

interface GameDetailsRepository {
    suspend fun getGameDetails(id: Int) : Result<GameDetails>
}

