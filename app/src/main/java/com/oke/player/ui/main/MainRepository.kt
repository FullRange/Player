package com.oke.player.ui.main

import com.oke.player.model.api.MovieApi
import com.oke.player.model.api.ResultWrapper
import com.oke.player.model.dao.Dao
import com.oke.player.model.entity.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MainRepository {

    suspend fun getAll(): List<Item>?
    suspend fun insertAll(items: List<Item>)
    suspend fun deleteAll()

    suspend fun refresh(text: String)
}

class MainRepositoryImpl @Inject constructor(
    private val dao: Dao,
    private val api: MovieApi
): MainRepository {

    override suspend fun getAll(): List<Item>? = withContext(Dispatchers.IO) { dao.getAll() }
    override suspend fun insertAll(items: List<Item>) = withContext(Dispatchers.IO) { dao.refresh(items) }
    override suspend fun deleteAll() = withContext(Dispatchers.IO) { dao.deleteAll() }

    override suspend fun refresh(text: String) {
        val response = api.getMovieList(text)

        if (response is ResultWrapper.Success && response.value.isSuccessful) {
            insertAll( response.value.body()?.map {
                Item( name = it.show?.name, type = it.show?.type, image = it.show?.image?.medium)
            } ?: emptyList())
        }
    }
}