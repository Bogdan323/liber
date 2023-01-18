package com.example.liber.data.remote.dto.allgames

import com.example.liber.domain.model.AddedByStatus
import com.google.gson.annotations.SerializedName

data class AddedByStatusDto(
    @SerializedName("beaten")
    val beaten: Int,
    @SerializedName("dropped")
    val dropped: Int,
    @SerializedName("owned")
    val owned: Int,
    @SerializedName("playing")
    val playing: Int,
    @SerializedName("toplay")
    val toplay: Int,
    @SerializedName("yet")
    val yet: Int
)

fun AddedByStatusDto.toAddedByStatus() : AddedByStatus {
    return AddedByStatus(
        beaten = beaten,
        dropped = dropped,
        owned = owned,
        playing = playing,
        toplay = toplay,
        yet = yet
    )
}