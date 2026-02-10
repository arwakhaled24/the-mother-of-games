package com.example.themotherofgames.domain.usecase

import com.example.themotherofgames.domain.model.GameDetails
import com.example.themotherofgames.domain.repositories.GameDetailsRepository

class GetGameDetailsUseCase(private val gameDetailsRepository: GameDetailsRepository) {
    suspend operator fun invoke(id: Int): Result<GameDetails> {
        return gameDetailsRepository.getGameDetails(id)
    }
}