package com.example.liber.domain.repository

import androidx.paging.PagingData
import com.example.liber.data.remote.dto.allgames.ResultDto
import com.example.liber.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    //Remote
    suspend fun getAllGames(): Flow<PagingData<Game>>
    suspend fun searchGames(searchText: String): Flow<PagingData<Game>>
}