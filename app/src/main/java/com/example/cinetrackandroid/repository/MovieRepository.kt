package com.example.cinetrackandroid.repository

import kotlinx.coroutines.flow.Flow
import com.example.cinetrackandroid.data.Movie
import com.example.cinetrackandroid.data.MovieDataSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDataSource: MovieDataSource
) {
    fun getMovies(): Flow<List<Movie>> = movieDataSource.movies

    fun toggleFavorite(id: Int) = movieDataSource.toggleFavorite(id)

}
