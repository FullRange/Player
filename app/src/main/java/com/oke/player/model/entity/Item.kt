package com.oke.player.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oke.player.model.entity.Item.Companion.TABLE

@Entity(
    tableName = TABLE
)
data class Item(
    val name: String = "none"
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        const val TABLE: String = "items"
    }
}