package com.example.themotherofgames.data.repositories

import com.example.themotherofgames.data.mapper.toGameDetails
import com.example.themotherofgames.data.remote.GamesKtorService
import com.example.themotherofgames.domain.model.GameDetails
import com.example.themotherofgames.domain.repositories.GameDetailsRepository

class GameDetailsRepositoryImpl(private val apiService: GamesKtorService)  : GameDetailsRepository {
    override suspend fun getGameDetails(id: Int): Result<GameDetails> {
        val response = apiService.getGameDetails(id)
        return Result.success(response.toGameDetails())
    }
}