package com.example.hailtask.ui.itemDetails

import android.util.Log
import androidx.lifecycle.*
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.util.Constants
import com.example.hailtask.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemDetailsViewModel(private val repositoryDet: ItemDeatailsRepo, itemId: Int) : ViewModel() {
    private val _filteredCodesRes = MutableLiveData(itemId)
    val itemDetails: LiveData<ItemDetailClass> =
        _filteredCodesRes.switchMap { repositoryDet.getItemDetailsLiveData(it) }

    init {
        fetchAndSaveItemDetails(itemId)
    }

    fun fetchAndSaveItemDetails(itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repositoryDet.getItemDet(Constants.auth, Constants.apiKey, itemId)
                if (response.isSuccessful) {
                    val itemDetailsResponse = response.body()
                    itemDetailsResponse?.let { itemDetailsResponse ->
                        val itemDetails = itemDetailsResponse.data.item_details
                        repositoryDet.saveItemDetailsToDb(itemDetails)
                    }
                } else {
                    Log.e("eeee", "Failed to fetch item details: ${response.message()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
