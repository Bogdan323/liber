package com.example.liber.data.remote.api

import com.example.liber.data.remote.dto.allgames.ApiResponseDto
import com.example.liber.common.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {

    //Get all games
    @GET("games?key=${API_KEY}")
    suspend fun getAllGames(
        @Query("page") page: Int
    ) : ApiResponseDto

    //Search games
    @GET("games?key=${API_KEY}")
    suspend fun searchGames(
        @Query("page") page: Int,
        @Query("search") search: String = ""
    ) : ApiResponseDto
}