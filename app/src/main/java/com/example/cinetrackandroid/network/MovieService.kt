package com.example.cinetrackandroid.network

import com.example.cinetrackandroid.network.dto.MovieListResponseDto
import retrofit2.http.GET


interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieListResponseDto

    @GET("movie/now_playing")
    suspend fun getRecentMovies(): MovieListResponseDto
}