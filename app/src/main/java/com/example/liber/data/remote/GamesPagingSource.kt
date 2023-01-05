package com.example.liber.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.liber.data.remote.api.GamesApi
import com.example.liber.data.remote.dto.allgames.ResultDto
import com.example.liber.data.remote.dto.allgames.toGame
import com.example.liber.domain.model.Game
import java.io.IOException
import javax.inject.Inject


class GamesPagingSource @Inject constructor(private val api: GamesApi) : PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
//        return state.anchorPosition?.let {
//            state.closestItemToPosition(it)?.id
//        }
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key ?: 1
            val response = api.getAllGames(page = page)
            val games = response.resultDtos
                .map(ResultDto::toGame)
            val nextPage = if (response.next != null) page + 1 else null

            LoadResult.Page(
                data = games,
                prevKey = null,
                nextKey = nextPage
            )
        }
        catch (e: IOException) {
            LoadResult.Error(e)
        }
        catch (e: java.lang.NullPointerException) {
            LoadResult.Error(e)
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}