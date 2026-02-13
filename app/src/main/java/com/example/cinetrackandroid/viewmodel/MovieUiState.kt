package com.example.cinetrackandroid.viewmodel

import com.example.cinetrackandroid.data.Movie

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    // val isLoading: Boolean = false
    val errorMessage: String? = null
)
