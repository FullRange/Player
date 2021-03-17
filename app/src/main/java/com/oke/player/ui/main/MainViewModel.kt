package com.oke.player.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _state = MutableLiveData<MainState>()

    private val currentState get() = _state.value ?: MainState()

    val state: LiveData<MainState> get() = _state
}