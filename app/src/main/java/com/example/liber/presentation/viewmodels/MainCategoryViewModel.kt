package com.example.liber.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.liber.domain.model.Game
import com.example.liber.domain.usecase.GetAllGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel @Inject constructor(
    private val getAllGamesUseCase: GetAllGamesUseCase
) : ViewModel() {

    private val _listOfAllGames = MutableStateFlow<PagingData<Game>>(PagingData.empty())
    val listOfAllGames : StateFlow<PagingData<Game>> = _listOfAllGames

    init {
        getAllGames()
    }

    private fun getAllGames() {
        viewModelScope.launch {
            getAllGamesUseCase()
                .cachedIn(viewModelScope)
                .collect {
                    _listOfAllGames.value = it
                }
        }
    }
}