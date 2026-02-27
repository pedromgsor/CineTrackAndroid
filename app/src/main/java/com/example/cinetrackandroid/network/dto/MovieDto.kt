package com.example.cinetrackandroid.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String = "",
    val overview: String = "",
    @SerialName("vote_average") val rating: Double = 0.0,
    val popularity: Double = 0.0,
    @SerialName("release_date") val releaseDate: String = ""
)
