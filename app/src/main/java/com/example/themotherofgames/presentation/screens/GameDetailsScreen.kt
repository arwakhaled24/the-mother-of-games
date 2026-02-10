package com.example.themotherofgames.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.themotherofgames.presentation.compossable.ErrorView
import com.example.themotherofgames.presentation.compossable.GameDetailsContent
import com.example.themotherofgames.presentation.compossable.InitialLoadingIndicator
import com.example.themotherofgames.presentation.viewmodel.gamedetails.GameDetailsAction
import com.example.themotherofgames.presentation.viewmodel.gamedetails.GameDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameDetailsScreen(
    gameId: Int,
    onBack: () -> Unit,
    viewModel: GameDetailsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    DisposableEffect(gameId) {
        viewModel.initialize(gameId)
        onDispose { }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                InitialLoadingIndicator()
            }
            state.error != null && state.gameDetails == null -> {
                ErrorView(
                    message = state.error ?: "Error loading game details",
                    onRetry = { viewModel.sendAction(GameDetailsAction.RetryLoading) }
                )
            }
            state.gameDetails != null -> {
                GameDetailsContent(
                    state = state,
                    onBack = onBack,
                    onToggleDescription = { 
                        viewModel.sendAction(GameDetailsAction.ToggleDescription)
                    }
                )
            }
        }
    }
}




