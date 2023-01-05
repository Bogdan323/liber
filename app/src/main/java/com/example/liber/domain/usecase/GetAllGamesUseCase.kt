package com.example.liber.domain.usecase

import androidx.paging.PagingData
import com.example.liber.domain.model.Game
import com.example.liber.domain.repository.GamesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetAllGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Game>> = repository.getAllGames()
}