package com.example.liber.data.remote.dto.allgames

import com.example.liber.domain.model.EsrbRating
import com.google.gson.annotations.SerializedName

data class EsrbRatingDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)

fun EsrbRatingDto.toEsrbRating() : EsrbRating {
    return EsrbRating(
        id = id,
        name = name,
        slug = slug
    )
}