package com.example.cinetrackandroid.data

import com.example.cinetrackandroid.network.dto.MovieDto

fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    rating = rating,
    popularity = popularity,
    releaseDate = releaseDate
)