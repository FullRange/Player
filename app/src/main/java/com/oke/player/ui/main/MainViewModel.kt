package com.oke.player.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oke.player.ui.main.list.MovieItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _state = MutableLiveData<MainState>()

    private val currentState get() = _state.value ?: MainState()

    val state: LiveData<MainState> get() = _state

    fun onSearch(text: String) {
        viewModelScope.launch {
            repository.refresh(text)
            _state.value = currentState.copy(list = repository.getAll()?.map {
                MovieItem(name = it.name, type = it.type, image = it.image)
            })
        }
    }
}