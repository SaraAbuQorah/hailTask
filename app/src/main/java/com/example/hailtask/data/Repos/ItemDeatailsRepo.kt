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
    fun getItemDetailsLiveData(id: Int): LiveData<Resource<ItemDetailClass>> {
        var i:LiveData<Resource<ItemDetailClass>>
        val existingItemDetails = itemDatabase.itemDao().getItemDetails(id)
         if (existingItemDetails != null) {
            i=existingItemDetails.map { itemDetails ->
                Log.e("here","${itemDetails}")
                Resource.Success(itemDetails)
            }
        } else {

             return fetchAndSaveItemDetails(id)
        }
        return i
    }

    fun fetchAndSaveItemDetails(id: Int): LiveData<Resource<ItemDetailClass>> {
        val resultLiveData = MutableLiveData<Resource<ItemDetailClass>>()
        GlobalScope.launch {
            try {
                val response = itemDetailsHelper.getItemDet(Constants.auth, Constants.apiKey, id)
                if (response.isSuccessful) {
                    val itemDetailsResponse = response.body()
                    itemDetailsResponse?.let { itemDetailsResponse ->
                        val itemDetails = itemDetailsResponse.data.item_details
                        saveItemDetailsToDb(itemDetails)
                        resultLiveData.postValue(Resource.Success(itemDetails))
                    } ?: resultLiveData.postValue(Resource.Error("Empty response body"))
                } else {
                    Log.e("eeee", "Failed to fetch item details: ${response.message()}")
                    resultLiveData.postValue(Resource.Error("Failed to fetch item details: ${response.message()}"))
                }
            } catch (e: Exception) {
                resultLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
        return resultLiveData
    }
    private  fun saveItemDetailsToDb(itemDetails: ItemDetailClass) {
        itemDatabase.itemDao().insertItemDetails(itemDetails)
        Log.d("ItemDeatailsRepo", "Item details added to the database")
    }
}
