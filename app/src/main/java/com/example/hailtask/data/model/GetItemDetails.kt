package com.example.hailtask.data.model

import com.example.hailtask.data.model.Data

data class GetItemDetails(
    val `data`: Data,
    val message: String,
    val status: String
)