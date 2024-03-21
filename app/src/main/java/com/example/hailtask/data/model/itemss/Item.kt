package com.example.hailtask.data.model.itemss

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Items")
data class Item(
    val address: String,
    val created_at: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val is_favorite: Int,
    val name: String,
    val rate: Int,
    val sub_category_name: String
)