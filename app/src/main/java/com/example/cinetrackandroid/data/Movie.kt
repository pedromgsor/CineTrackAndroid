package org.example.cinetrack.data

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val rating: Double,
    val releaseDate: String,
    val isFavorite: Boolean = false
)