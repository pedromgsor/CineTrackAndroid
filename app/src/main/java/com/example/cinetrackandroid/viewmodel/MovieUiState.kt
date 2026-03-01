package com.example.cinetrackandroid.viewmodel

import com.example.cinetrackandroid.data.Movie

enum class MovieCategory {
    POPULAR,
    RECENT,
}

enum class SortOption {
    POPULARITY,
    TITLE,
    NEWEST,
    OLDEST,
}


data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val category: MovieCategory = MovieCategory.POPULAR,
    val sortOption: SortOption = SortOption.POPULARITY
)
