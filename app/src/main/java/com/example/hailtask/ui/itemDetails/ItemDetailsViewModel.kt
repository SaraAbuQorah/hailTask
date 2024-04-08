package com.example.hailtask.ui.itemDetails

import android.util.Log
import androidx.lifecycle.*

import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.util.Constants
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.SavedStateHandle
import javax.inject.Inject

@HiltViewModel()
class ItemDetailsViewModel @Inject  constructor(private val repositoryDet: ItemDeatailsRepo,
                                                val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val itemId: Int? = savedStateHandle["IdArg"]

    private val _filteredCodesRes = MutableLiveData(itemId)
    val itemDetails: LiveData<ItemDetailClass> =
        _filteredCodesRes.switchMap { repositoryDet.getItemDetailsLiveData(it!!) }


    init {
        fetchAndSaveItemDetails(itemId!!)
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
