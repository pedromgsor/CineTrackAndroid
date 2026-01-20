package org.example.cinetrack.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieDataSource {

    private val _movies = MutableStateFlow(
        listOf(
            Movie(
                id = 1,
                title = "Inception",
                overview = "A thief who steals corporate secrets through dream-sharing technology.",
                rating = 8.8,
                releaseDate = "2010-07-16"
            ),
            Movie(
                id = 2,
                title = "Interstellar",
                overview = "A team of explorers travels through a wormhole in space.",
                rating = 8.6,
                releaseDate = "2014-11-07"
            ),
            Movie(
                id = 3,
                title = "The Dark Knight",
                overview = "Batman faces the Joker, a criminal mastermind.",
                rating = 9.0,
                releaseDate = "2008-07-18"
            )
        )
    )

    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    fun toggleFavorite(id: Int) {
        _movies.update { currentList ->
            currentList.map { movie ->
                if (movie.id == id) {
                    movie.copy(isFavorite = !movie.isFavorite)
                } else {
                    movie
                }
            }
        }
    }
}