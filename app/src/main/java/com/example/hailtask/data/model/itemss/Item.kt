package com.example.hailtask.data.model.itemss

data class Item(
    val address: String,
    val created_at: String,
    val id: Int,
    val image: String,
    val is_favorite: Int,
    val name: String,
    val original_rate: Any,
    val rate: Int,
    val sub_category_name: String
)