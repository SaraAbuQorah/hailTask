package com.example.hailtask.data.Repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hailtask.data.Api.Items.ItemsHelper
import com.example.hailtask.data.model.itemss.GetItems
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.room.ItemDataBase
import com.example.hailtask.util.Constants
import com.example.hailtask.util.Resource
import retrofit2.Response

class ItemsRepo(private val itemDatabase: ItemDataBase) {

    private val itemsHelper = ItemsHelper.api

    fun getItemsLiveData(): LiveData<Resource<List<Item>>> {
        return itemDatabase.itemDao().getItems().map { items ->
            Resource.Success(items)
        }
    }

    suspend fun getItemsAndSaveToDb(): Resource<Unit> {
            return try {
                val response = itemsHelper.getItems(Constants.auth, Constants.apiKey)
                if (response.isSuccessful) {
                    response.body()?.data?.items?.let { items ->
                        saveItemsToDb(items)
                    }
                    Resource.Success(Unit)
                } else {
                    Resource.Error("Failed to fetch items: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("An error occurred: ${e.message}")
            }
        }

    private suspend fun saveItemsToDb(items: List<Item>) {
        itemDatabase.itemDao().insert(items)
    }
}
