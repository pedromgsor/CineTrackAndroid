package org.example.cinetrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.cinetrack.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor() : ViewModel() {
    private val movieRepository = MovieRepository()

    val uiState: StateFlow<MovieUiState> =
        movieRepository.getMovies().map { movies -> MovieUiState(movies = movies) }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, MovieUiState()
        )
}