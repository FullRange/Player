package com.oke.player.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oke.player.model.entity.Item.Companion.TABLE

@Entity(
    tableName = TABLE
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?,
    val type: String?,
    val image: String?
) {
    companion object {
        const val TABLE: String = "items"
    }
}