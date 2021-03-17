package com.oke.player.ui.main

import com.oke.player.model.dao.Dao
import com.oke.player.model.entity.Item
import javax.inject.Inject

interface MainRepository {

    suspend fun getAll(): List<Item>?
    suspend fun insertOrReplace(item: Item)
    suspend fun deleteAll()
}

class MainRepositoryImpl @Inject constructor(
    private val dao: Dao
): MainRepository {

    override suspend fun getAll(): List<Item>? = dao.getAll()
    override suspend fun insertOrReplace(item: Item) = dao.insert(item)
    override suspend fun deleteAll() = dao.deleteAll()
}