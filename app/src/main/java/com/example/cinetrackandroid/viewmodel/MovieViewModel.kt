package com.example.cinetrackandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.example.cinetrackandroid.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val uiState: StateFlow<MovieUiState> =
        movieRepository.getMovies().map { movies -> MovieUiState(movies = movies) }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), MovieUiState() // Changed to WhileSubscribed
        )
}