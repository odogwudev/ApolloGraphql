package com.odogwudev.apollographql.presentation.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odogwudev.apollographql.data.common.RickAndMortyResult
import com.odogwudev.apollographql.domain.models.CharactersModel
import com.odogwudev.apollographql.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersViewModel @ViewModelInject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _resultListCharacters = MutableLiveData<RickAndMortyResult<CharactersModel>>()
    val resultListCharacters: LiveData<RickAndMortyResult<CharactersModel>> = _resultListCharacters

    fun getListCharacters(page: Int) {
        viewModelScope.launch {
            getCharactersUseCase(page).collect {
                _resultListCharacters.postValue(it)
            }
        }
    }
}