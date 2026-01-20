package org.example.cinetrack.repository

import kotlinx.coroutines.flow.Flow
import org.example.cinetrack.data.Movie
import org.example.cinetrack.data.MovieDataSource

class MovieRepository(
    private val movieDataSource: MovieDataSource = MovieDataSource()
) {

    fun getMovies(): Flow<List<Movie>> = movieDataSource.movies

    fun toggleFavorite(id: Int) = movieDataSource.toggleFavorite(id)

}

