package com.example.liber.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Game(
    val added: Int?,
    val addedByStatus: @RawValue AddedByStatus?,
    val backgroundImage: String?,
    val dominantColor: String?,
    val esrbRating: @RawValue EsrbRating?,
    val genre: @RawValue List<Genre>?,
    val id: Int,
    val parentPlatform: @RawValue List<ParentPlatform>?,
    val metacritic: Int?,
    val name: String?,
    val playtime: Int?,
    val rating: Double?,
    val ratingTop: Int?,
    val ratingsCount: Int?,
    val released: String?,
    val reviewsCount: Int?,
    val reviewsTextCount: Int?,
    val saturatedColor: String?,
    val shortScreenshot: @RawValue List<ShortScreenshot>?,
    val slug: String?,
    val suggestionsCount: Int?,
    val tba: Boolean?,
    val updated: String?,
) : Parcelable