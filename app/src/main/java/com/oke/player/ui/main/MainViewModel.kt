package com.oke.player.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oke.player.model.entity.Item
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _state = MutableLiveData<MainState>()

    private val currentState get() = _state.value ?: MainState()

    val state: LiveData<MainState> get() = _state

    init {
        viewModelScope.launch {
            repository.insertOrReplace(Item(name = "Test1"))
            repository.insertOrReplace(Item(name = "Test2"))

            _state.value = currentState.copy(text = repository.getAll().toString())
        }
    }
}