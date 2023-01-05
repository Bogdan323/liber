package com.example.liber.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.liber.data.remote.GamesPagingSource
import com.example.liber.data.remote.SearchGamesPagingSource
import com.example.liber.data.remote.api.GamesApi
import com.example.liber.domain.model.Game
import com.example.liber.domain.repository.GamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepositoryImpl @Inject constructor(
    private val remote: GamesApi
) : GamesRepository {

    override suspend fun getAllGames() : Flow<PagingData<Game>> {
        return Pager(
                config = PagingConfig(20, enablePlaceholders = false, initialLoadSize = 20),
                pagingSourceFactory = {
                    GamesPagingSource(api = remote)
                }
        ).flow
    }

    override suspend fun searchGames(searchText: String) : Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false, initialLoadSize = 20),
            pagingSourceFactory = {
                SearchGamesPagingSource(api = remote, searchText)
            }
        ).flow
    }

}