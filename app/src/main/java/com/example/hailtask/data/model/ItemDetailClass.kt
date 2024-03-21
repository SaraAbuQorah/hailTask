package com.example.hailtask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ItemDetails")
data class ItemDetailClass(
    val address: String,
    val close_time: String,
    val count_rates: Int,
    val created_at: String,
    val description: String,
    val email: Any,
    val end_date: Any,
    @PrimaryKey
    val id: Int,
    val images: List<String>,
    val is_favorite: Int,
    val lat: String,
    val link: String,
    val link_to_volunteer: Any,
    val lng: String,
    val name: String,
    val open_time: String,
    val original_rate: Int,
    val phone: String,
    val rate: Int,
    val start_date: Any,
    val sub_category_name: String
)