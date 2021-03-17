package com.oke.player.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oke.player.ui.main.list.MovieItem
import com.oke.player.util.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _state = MutableLiveData<MainState>()

    private val currentState get() = _state.value ?: MainState()

    val state: LiveData<MainState> get() = _state

    val effect = SingleLiveEvent<MainEffect>()

    fun onSearch(text: String) {
        viewModelScope.launch {
            repository.refresh(text)
            _state.value = currentState.copy(list = repository.getAll()?.map {
                MovieItem(name = it.name, type = it.type, image = it.image)
            })
        }
    }

    fun clearData() {
        viewModelScope.launch {
            repository.deleteAll()
            _state.value = currentState.copy(list = emptyList())
        }
    }

    fun onItemClicked() {
        effect.value = MainEffect.GoToPlayer
    }

    fun getVideoUrl() = videoUrl

    companion object {

        private const val videoUrl: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
    }
}