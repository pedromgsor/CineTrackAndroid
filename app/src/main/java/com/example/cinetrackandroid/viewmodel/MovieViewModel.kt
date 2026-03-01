package com.example.cinetrackandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinetrackandroid.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.example.cinetrackandroid.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    init {
        // Not yet created, but as soon as VM is created we need to
        //load data
        loadMovies()
    }

    private fun sortMovies(
        movies: List<Movie>,
        sort: SortOption
    ): List<Movie> {
        return when (sort) {
            SortOption.POPULARITY -> movies.sortedBy { it.popularity}
            SortOption.TITLE -> movies.sortedBy { it.title.lowercase() }
            SortOption.NEWEST -> movies.sortedByDescending { it.releaseDate }
            SortOption.OLDEST -> movies.sortedBy { it.releaseDate }
        }
    }

    fun loadMovies() {
         viewModelScope.launch {
             _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching {
                when(_uiState.value.category) {
                    MovieCategory.POPULAR -> movieRepository.getPopularMovies()
                    MovieCategory.RECENT -> movieRepository.getRecentMovies()
                }
            }.onSuccess { movies ->
                val sorted = sortMovies(movies, _uiState.value.sortOption)
                _uiState.update { it.copy(movies = sorted, isLoading = false) }
            }.onFailure { error ->
                _uiState.update { it.copy( isLoading = false, errorMessage = error.message) }
            }
         }
    }

    fun setCategory(category: MovieCategory) {
        _uiState.update { it.copy(category = category) }
        // New list need to load it
        loadMovies()
    }

    fun setSortOption(sortOption: SortOption) {
        _uiState.update { state ->
            state.copy(sortOption = sortOption)
        }
        // Existing list only need to sort it
    }



}