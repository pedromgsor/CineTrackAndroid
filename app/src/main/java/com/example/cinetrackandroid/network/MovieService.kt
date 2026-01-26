package com.example.cinetrackandroid.network

import retrofit2.http.GET


interface MovieService {
    @GET("movie/popular")
    suspend fun getMovies()
}