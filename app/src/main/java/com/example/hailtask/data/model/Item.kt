package com.example.hailtask.data.model

import android.net.Uri

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