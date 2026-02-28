package com.example.cinetrackandroid.repository

import com.example.cinetrackandroid.data.Movie
import com.example.cinetrackandroid.data.MovieRemoteDataSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
) {
    suspend fun getPopularMovies(): List<Movie> {
        return remoteDataSource.getPopularMovies()
    }

    suspend fun getRecentMovies(): List<Movie> {
        return remoteDataSource.getRecentMovies()
    }

}
