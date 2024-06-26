package com.example.hailtask.data.Repos

import android.util.Log
import androidx.lifecycle.*
import com.example.hailtask.data.Api.ItemDetails.ItemDetailsInterFace
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.room.ItemDataBase
import javax.inject.Inject


class ItemDeatailsRepo  @Inject constructor(private val itemDatabase: ItemDataBase,
                       private val api : ItemDetailsInterFace) {


    fun getItemDetailsLiveData(id: Int): LiveData<ItemDetailClass> {
        val existingItemDetails = itemDatabase.itemDao().getItemDetails(id)
        Log.d("ItemDetailsRepoDB", "Item details added to the database")

        return existingItemDetails
    }


    fun saveItemDetailsToDb(itemDetails: ItemDetailClass) {
        itemDatabase.itemDao().insertItemDetails(itemDetails)
        Log.d("ItemDetailsRepo", "Item details added to the database")
    }

    suspend fun getItemDet(auth: String, apiKey: String, itemId: Int) =
        api.getItemDet(auth, apiKey, itemId)

}
