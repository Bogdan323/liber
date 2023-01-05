package com.example.liber.data.remote.dto.allgames


import com.example.liber.domain.model.ShortScreenshot
import com.google.gson.annotations.SerializedName

data class ShortScreenshotDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)

fun ShortScreenshotDto.toShortScreenshot() : ShortScreenshot {
    return ShortScreenshot(
        id = id,
        image = image
    )
}