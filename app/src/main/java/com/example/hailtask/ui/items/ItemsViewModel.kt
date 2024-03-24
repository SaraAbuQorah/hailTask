package com.example.hailtask.ui.items

import android.content.Context
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
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
class ItemsViewModel(private val repository: ItemsRepo) : ViewModel() {
    val itemsLiveData: LiveData<Resource<List<Item>>> = repository.getItemsLiveData()

    init{
        refreshItems()
    }
    fun refreshItems() {
        viewModelScope.launch {
                repository.getItemsAndSaveToDb()
            if(itemsLiveData.value==null)
                Log.e("No Data ","faild item save to database")
            else Log.e("data ","the data have  saved to database")
        }
    }
}


