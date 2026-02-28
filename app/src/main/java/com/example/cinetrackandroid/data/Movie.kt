package com.example.cinetrackandroid.data

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val rating: Double,
    val popularity: Double,
    val releaseDate: String,
    val isFavorite: Boolean = false,
    val watchList: Boolean = false,
)