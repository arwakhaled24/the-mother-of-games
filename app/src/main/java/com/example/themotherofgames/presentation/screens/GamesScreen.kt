package com.example.themotherofgames.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.themotherofgames.presentation.compossable.ErrorView
import com.example.themotherofgames.presentation.compossable.GamesList
import com.example.themotherofgames.presentation.compossable.InitialLoadingIndicator
import com.example.themotherofgames.presentation.util.PaginationState
import com.example.themotherofgames.presentation.viewmodel.games.GamesAction
import com.example.themotherofgames.presentation.viewmodel.games.GamesViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    viewModel: GamesViewModel = koinViewModel(),
    onGameClick: (Int) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("THE MOTHER OF GAMED") },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isInitialLoading -> InitialLoadingIndicator()
                state.paginationState is PaginationState.Error && state.games.isEmpty() -> {
                    ErrorView(
                        message = state.error ?: "Error loading games",
                        onRetry = { viewModel.sendAction(GamesAction.RetryLoading) }
                    )
                }
                else -> GamesList(state, viewModel, onGameClick)
            }
        }
    }
}

