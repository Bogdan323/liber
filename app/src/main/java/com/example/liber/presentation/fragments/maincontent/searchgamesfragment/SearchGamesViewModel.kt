package com.example.liber.presentation.fragments.maincontent.searchgamesfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.liber.domain.model.Game
import com.example.liber.domain.usecase.SearchGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchGamesViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase
) : ViewModel() {

    private val _listOfSearchedGames = MutableStateFlow<PagingData<Game>>(PagingData.empty())
    val listOfSearchedGames : StateFlow<PagingData<Game>> = _listOfSearchedGames

    fun getSearchedGames(searchGamesText: String) {
        viewModelScope.launch {
            searchGamesUseCase(searchGamesText)
                .cachedIn(viewModelScope)
                .collect {
                    _listOfSearchedGames.value = it
                }
        }
    }
}