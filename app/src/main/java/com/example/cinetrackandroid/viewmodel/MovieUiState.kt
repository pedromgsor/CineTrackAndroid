package org.example.cinetrack.viewmodel

import org.example.cinetrack.data.Movie

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    // val isLoading: Boolean = false
    val errorMessage: String? = null
)
