package com.oke.player.model.dao

import androidx.room.*
import androidx.room.Dao
import com.oke.player.model.entity.Item
import com.oke.player.model.entity.Item.Companion.TABLE

@Dao
interface Dao {

    @Query("select * from $TABLE")
    fun getAll(): List<Item>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Item>)

    @Query("delete from $TABLE")
    fun deleteAll()

    @Transaction
    fun refresh(items: List<Item>) {
        deleteAll()
        insertAll(items)
    }
}