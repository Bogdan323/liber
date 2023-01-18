package com.example.liber.data.remote.dto.allgames

import com.example.liber.domain.model.*
import com.google.gson.annotations.SerializedName

data class ApiResponseDto(
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val resultDtos: List<ResultDto>
)

fun ApiResponseDto.toApiResponse() : ApiResponse {
    return ApiResponse(
        next = next,
        previous = previous,
        games = resultDtos.map {
            Game(
                added = it.added,
                addedByStatus = AddedByStatus(
                    it.addedByStatusDto?.beaten,
                    it.addedByStatusDto?.dropped,
                    it.addedByStatusDto?.owned,
                    it.addedByStatusDto?.playing,
                    it.addedByStatusDto?.toplay,
                    it.addedByStatusDto?.yet
                ),
                backgroundImage = it.backgroundImage,
                dominantColor = it.dominantColor,
                esrbRating = EsrbRating(
                    it.esrbRatingDto?.id ?: 0,
                    it.esrbRatingDto?.name ?: "",
                    it.esrbRatingDto?.slug ?: ""
                ),
                genre = it.genreDtos?.map {
                    Genre(
                        it.gamesCount,
                        it.id,
                        it.imageBackground,
                        it.name,
                        it.slug
                    )
                },
                id = it.id,
                metacritic = it.metacritic,
                name = it.name,
                playtime = it.playtime,
                rating = it.rating,
                ratingTop = it.ratingTop,
                ratingsCount = it.ratingsCount,
                released = it.released,
                reviewsCount = it.reviewsCount,
                reviewsTextCount = it.reviewsTextCount,
                saturatedColor = it.saturatedColor,
                shortScreenshot = it.shortScreenshotDtos?.map {
                    ShortScreenshot(
                        it.id,
                        it.image
                    )
                },
                slug = it.slug,
                suggestionsCount = it.suggestionsCount,
                tba = it.tba,
                updated = it.updated,
                parentPlatform = it.parentPlatformsDto?.map{
                    ParentPlatform(
                        Platform(
                            it.platformDto.id,
                            it.platformDto.name,
                            it.platformDto.slug
                        )
                    )
                }
            )
        }
    )
}