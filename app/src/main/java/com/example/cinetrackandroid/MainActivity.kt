package com.example.cinetrackandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinetrackandroid.ui.theme.CineTrackAndroidTheme
import com.example.cinetrackandroid.viewmodel.MovieCategory
import com.example.cinetrackandroid.viewmodel.MovieUiState
import com.example.cinetrackandroid.viewmodel.MovieViewModel
import com.example.cinetrackandroid.viewmodel.SortOption
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTrackAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MovieAppContent(
    modifier: Modifier = Modifier,
    uiState: MovieUiState,
    onCategoryChange: (MovieCategory) -> Unit,
    onSortChange: (SortOption) -> Unit,
    onRetry: () -> Unit,
) {
    Column(modifier = modifier) {
        // Change category
        Row {
            TextButton(onClick = { onCategoryChange(MovieCategory.POPULAR) }) {
                Text(text = "Popular")
            }
            TextButton(onClick = { onCategoryChange(MovieCategory.RECENT) }) {
                Text(text = "Recent")
            }
        }
        // Change sorting order
        Row {
            TextButton(onClick = { onSortChange(SortOption.POPULARITY) }) {
                Text(text = "Popularity")
            }
            TextButton(onClick = { onSortChange(SortOption.OLDEST) }) {
                Text(text = "Oldest")
            }
            TextButton(onClick = { onSortChange(SortOption.NEWEST) }) {
                Text(text = "Newest")
            }
            TextButton(onClick = { onSortChange(SortOption.TITLE) }) {
                Text(text = "Title")
            }
        }
        when {
            uiState.isLoading -> {
                Text(text = "Loading...")
            }
            uiState.errorMessage != null -> {
                Text(text = uiState.errorMessage)
                TextButton(
                    onClick = onRetry,
                ) {
                    Text(text = "Retry")
                }
            }
            else -> {
                uiState.movies.forEach { movie ->
                    Text(text = movie.title)
                }
            }
        }
    }
}

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MovieAppContent(
        modifier = modifier,
        uiState = uiState,
        onCategoryChange = viewModel::setCategory,
        onSortChange = viewModel::setSortOption,
        onRetry = viewModel::loadMovies

    )
}
