package com.example.cinetrackandroid.data

import com.example.cinetrackandroid.network.MovieService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService,
) {
    suspend fun getPopularMovies(): List<Movie> {
        return movieService.getPopularMovies().results.map { movieDto ->
            movieDto.toDomain()
        }
    }

    suspend fun getRecentMovies(): List<Movie> {
        return movieService.getRecentMovies().results.map { movieDto ->
            movieDto.toDomain()
        }
    }

}