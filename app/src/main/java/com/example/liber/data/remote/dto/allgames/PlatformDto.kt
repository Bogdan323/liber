package com.example.liber.data.remote.dto.allgames

import com.google.gson.annotations.SerializedName

data class PlatformDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)