package com.oke.player.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oke.player.model.entity.Item
import com.oke.player.model.entity.Item.Companion.TABLE

@Dao
interface Dao {

    @Query("select * from $TABLE")
    suspend fun getAll(): List<Item>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Query("delete from $TABLE")
    suspend fun deleteAll()
}