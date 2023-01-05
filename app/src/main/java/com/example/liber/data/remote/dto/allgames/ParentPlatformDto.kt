package com.example.liber.data.remote.dto.allgames


import com.google.gson.annotations.SerializedName

data class ParentPlatformDto(
    @SerializedName("platform")
    val platformDto: PlatformDto
)