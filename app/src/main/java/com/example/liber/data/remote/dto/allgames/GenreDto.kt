package com.example.liber.data.remote.dto.allgames


import com.example.liber.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("games_count")
    val gamesCount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_background")
    val imageBackground: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)

fun GenreDto.toGenre() : Genre {
    return Genre(
        gamesCount = gamesCount,
        id = id,
        imageBackground = imageBackground,
        name = name,
        slug = slug
    )
}