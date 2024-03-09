package com.example.hailtask.data.Repos
import com.example.hailtask.data.Api.ItemsHelper
import com.example.hailtask.data.Api.ItemsInterface
import com.example.hailtask.util.Constants


class ItemsRepo {
    suspend fun getItems() = ItemsHelper.api.getItems(Constants.auth,Constants.apiKey)
}