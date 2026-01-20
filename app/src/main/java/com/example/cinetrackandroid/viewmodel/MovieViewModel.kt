package org.example.cinetrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.cinetrack.repository.MovieRepository

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    val uiState: StateFlow<MovieUiState> =
        movieRepository.getMovies().map { movies -> MovieUiState(movies = movies) }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, MovieUiState()
        )
}