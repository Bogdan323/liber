package com.example.liber.data.remote.dto.allgames

import com.example.liber.domain.model.*
import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("added")
    val added: Int?,
    @SerializedName("added_by_status")
    val addedByStatusDto: AddedByStatusDto?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("parent_platforms")
    val parentPlatformsDto: List<ParentPlatformDto>?,
    @SerializedName("dominant_color")
    val dominantColor: String?,
    @SerializedName("esrb_rating")
    val esrbRatingDto: EsrbRatingDto?,
    @SerializedName("genres")
    val genreDtos: List<GenreDto>?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("metacritic")
    val metacritic: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("playtime")
    val playtime: Int?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("rating_top")
    val ratingTop: Int?,
    @SerializedName("ratings_count")
    val ratingsCount: Int?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("reviews_count")
    val reviewsCount: Int?,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int?,
    @SerializedName("saturated_color")
    val saturatedColor: String?,
    @SerializedName("short_screenshots")
    val shortScreenshotDtos: List<ShortScreenshotDto>?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int?,
    @SerializedName("tba")
    val tba: Boolean?,
    @SerializedName("updated")
    val updated: String?
)

fun ResultDto.toGame() : Game {
    return Game(
        added = added,
        addedByStatus = AddedByStatus(
            addedByStatusDto?.beaten ?: 0,
            addedByStatusDto?.dropped ?: 0,
            addedByStatusDto?.owned ?: 0,
            addedByStatusDto?.playing ?: 0,
            addedByStatusDto?.toplay ?: 0,
            addedByStatusDto?.yet ?: 0
        ),
        backgroundImage = backgroundImage,
        dominantColor = dominantColor,
        esrbRating = EsrbRating(
            esrbRatingDto?.id ?: 0,
            esrbRatingDto?.name ?: "",
            esrbRatingDto?.slug ?: ""
        ),
        genre = genreDtos?.map {
            Genre(
                it.gamesCount,
                it.id,
                it.imageBackground,
                it.name,
                it.slug
            )
        },
        id = id,
        metacritic = metacritic,
        name = name,
        playtime = playtime,
        rating = rating,
        ratingTop = ratingTop,
        ratingsCount = ratingsCount,
        released = released,
        reviewsCount = reviewsCount,
        reviewsTextCount = reviewsTextCount,
        saturatedColor = saturatedColor,
        shortScreenshot = shortScreenshotDtos?.map {
            ShortScreenshot(
                it.id,
                it.image
            )
        },
        slug = slug,
        suggestionsCount = suggestionsCount,
        tba = tba,
        updated = updated,
        parentPlatform = parentPlatformsDto?.map {
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