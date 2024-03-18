package com.example.hailtask.ui.itemDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.data.model.GetItemDetails
import com.example.hailtask.ui.items.ItemsViewModel
import com.example.hailtask.util.Resource
import kotlinx.coroutines.launch
import androidx.navigation.fragment.navArgs
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.ui.items.firstFragmentDirections
import com.google.android.material.imageview.ShapeableImageView
import kotlin.properties.Delegates


class ItemDetailsViewModel(private val repositoryDet: ItemDeatailsRepo) : ViewModel() {
    val itemDetLiveData = MutableLiveData<Resource<GetItemDetails>>()

     fun getItemDet(itemId:Int) {
        viewModelScope.launch {
            try {
                itemDetLiveData.postValue(Resource.Loading())
                val response = repositoryDet.getItemDet(itemId)
                if (response.isSuccessful) {
                    itemDetLiveData.postValue(Resource.Success(response.body()!!))
                    Log.e("successItemDetailis","start....")
                }
                else{
                    itemDetLiveData.value = Resource.Error(response.message())
                    Log.e("FailedItemDetailis","End...")

                }

            } catch (e: Exception) {
                itemDetLiveData.value = Resource.Error(e.message!!)
                Log.e("FailedItemDetailis","Catch End...")

            }
        }
    }

    }