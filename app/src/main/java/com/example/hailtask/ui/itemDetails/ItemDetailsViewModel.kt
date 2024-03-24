package com.example.hailtask.ui.itemDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.util.Resource
import kotlinx.coroutines.launch
class ItemDetailsViewModel(private val repositoryDet: ItemDeatailsRepo,itemId: Int) : ViewModel() {

    val itemDetailsLiveData: LiveData<Resource<ItemDetailClass>>? =repositoryDet.getItemDetailsLiveData(itemId)

    init {
        fetchAndSaveItemDetails(itemId)
    }
    fun fetchAndSaveItemDetails(itemId: Int) {
        viewModelScope.launch {
            try {
                repositoryDet.getItemDetailsLiveData(itemId)

            } catch (e: Exception) {
                Log.e("sfgdf","gfdsa")
            }
        }
    }


}
