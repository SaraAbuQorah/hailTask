package com.example.hailtask.data.Repos

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.*
import com.example.hailtask.data.Api.ItemDetails.ItemDetailsHelper
import com.example.hailtask.data.model.GetItemDetails
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.room.ItemDataBase
import com.example.hailtask.util.Constants
import com.example.hailtask.util.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ItemDeatailsRepo(private val itemDatabase: ItemDataBase) {

    private val itemDetailsHelper = ItemDetailsHelper.api
    fun getItemDetailsLiveData(id: Int): LiveData<ItemDetailClass> {
        val existingItemDetails = itemDatabase.itemDao().getItemDetails(id)
        return existingItemDetails
    }


    fun saveItemDetailsToDb(itemDetails: ItemDetailClass) {
        itemDatabase.itemDao().insertItemDetails(itemDetails)
        Log.d("ItemDetailsRepo", "Item details added to the database")
    }

    suspend fun getItemDet(auth: String, apiKey: String, itemId: Int) =
        itemDetailsHelper.getItemDet(auth, apiKey, itemId)

}
