package com.example.cinetrackandroid.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponseDto(
    val page: Int = 1,
    val results: List<MovieDto> = emptyList()
)
