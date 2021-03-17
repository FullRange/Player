package com.oke.player.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oke.player.model.entity.Item

private const val DB_VERSION = 1

@Database(
    entities = [
        Item::class
    ],
    version = DB_VERSION
)

abstract class AppDB: RoomDatabase() {

    abstract fun dao(): Dao
}