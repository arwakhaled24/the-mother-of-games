package com.example.themotherofgames.presentation.compossable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.themotherofgames.presentation.util.PaginationState
import com.example.themotherofgames.presentation.viewmodel.games.GamesAction
import com.example.themotherofgames.presentation.viewmodel.games.GamesState
import com.example.themotherofgames.presentation.viewmodel.games.GamesViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
@Composable
 fun GamesList(
    state: GamesState,
    viewModel: GamesViewModel,
    onGameClick: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisible >= totalItems - 3
        }
            .distinctUntilChanged()
            .collect { shouldLoad ->
                if (shouldLoad && state.canLoadMore) {
                    viewModel.sendAction(GamesAction.LoadMoreGames)
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = state.games, key = { it.id }) { game ->
            GameCard(
                game = game,
                onClick = { onGameClick(game.id) }
            )
        }

        if (state.isPaginationLoading) {
            item(key = "loading") {
                PaginationLoadingIndicator()
            }
        }

        if (state.paginationState is PaginationState.Error) {
            item(key = "error") {
                ErrorView(
                    message = state.error ?: "Error loading more",
                    onRetry = { viewModel.sendAction(GamesAction.RetryLoading) }
                )
            }
        }

        if (state.paginationState is PaginationState.EndReached) {
            item(key = "end") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No more games")
                }
            }
        }
    }
}