package com.example.themotherofgames.di

import com.example.themotherofgames.data.remote.GamesKtorService
import com.example.themotherofgames.data.repositories.GameDetailsRepositoryImpl
import com.example.themotherofgames.data.repositories.GameRepositoryImpl
import com.example.themotherofgames.domain.repositories.GameDetailsRepository
import com.example.themotherofgames.domain.repositories.GamesRepository
import com.example.themotherofgames.domain.usecase.GetGameDetailsUseCase
import com.example.themotherofgames.domain.usecase.GetGamesUseCase
import com.example.themotherofgames.presentation.viewmodel.gamedetails.GameDetailsViewModel
import com.example.themotherofgames.presentation.viewmodel.games.GamesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }

            install(Logging) {
                level = LogLevel.INFO
            }
        }
    }
    single<GamesKtorService> {
        GamesKtorService(
            client = get(),
        )
    }
    single<GamesRepository> {
        GameRepositoryImpl(apiService = get())
    }
    single<GameDetailsRepository> {
        GameDetailsRepositoryImpl(apiService = get())
    }
    single {
        GetGamesUseCase(
            gamesRepository = get()
        )
    }
    single {
        GetGameDetailsUseCase(
            gameDetailsRepository = get()
        )
    }
    viewModel { GamesViewModel(getGamesUseCase = get()) }
    viewModel { GameDetailsViewModel(getGameDetailsUseCase = get()) }
}