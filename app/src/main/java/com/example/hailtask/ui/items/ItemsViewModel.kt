package com.example.hailtask.ui.items

import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hailtask.data.Repos.ItemsRepo

import com.example.hailtask.data.model.itemss.GetItems
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.ui.itemDetails.ItemDetailsFragment
import com.example.hailtask.ui.itemDetails.ItemDetailsViewModel
import com.example.hailtask.util.Resource
import kotlinx.coroutines.launch

class ItemsViewModel(private val repository: ItemsRepo) : ViewModel()
     {


    val itemsLiveData = MutableLiveData<Resource<GetItems>>()
    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            try {
                itemsLiveData.postValue(Resource.Loading())
                val response = repository.getItems()
                if (response.isSuccessful) {
                    itemsLiveData.postValue(Resource.Success(response.body()!!))
                    Log.e("success","start....")
                }
                else{
                    itemsLiveData.value = Resource.Error(response.message())
                    Log.e("failed","End...")

                }

            } catch (e: Exception) {
                itemsLiveData.value = Resource.Error(e.message!!)
                Log.e("Failed","Catch End...")

            }
        }
    }

}
