package com.example.hailtask.data.Repos

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hailtask.data.Api.Items.ItemsHelper
import com.example.hailtask.data.Api.Items.ItemsInterface
import com.example.hailtask.data.model.itemss.GetItems
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.room.ItemDataBase
import com.example.hailtask.util.Constants
import com.example.hailtask.util.Resource
import retrofit2.Response
import javax.inject.Inject

class ItemsRepo  @Inject constructor(private val itemDatabase: ItemDataBase,
                                     private val apiItems : ItemsInterface, ) {



    fun getItemsLiveData(): LiveData<List<Item>> {
        return itemDatabase.itemDao().getItems()
    }

    suspend fun getItemsAndSaveToDb(): List<Item>? {
            val response = apiItems.getItems(Constants.auth, Constants.apiKey)
            if (response.isSuccessful) {
                response.body()?.data?.items?.let { items ->
                    saveItemsToDb(items)
                   return items
                }
            }
        return null
    }


    private suspend fun saveItemsToDb(items: List<Item>) {
        itemDatabase.itemDao().insert(items)
    }
}
