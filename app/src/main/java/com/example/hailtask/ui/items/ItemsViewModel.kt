package com.example.hailtask.ui.items

import android.content.Context
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hailtask.data.Repos.ItemsRepo

import com.example.hailtask.data.model.itemss.GetItems
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.ui.itemDetails.ItemDetailsFragment
import com.example.hailtask.ui.itemDetails.ItemDetailsViewModel
import com.example.hailtask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel  @Inject constructor(private val repository: ItemsRepo) : ViewModel() {
    private val mutableLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    val itemsLiveData: LiveData<List<Item>> = mutableLiveData.switchMap { repository.getItemsLiveData() }

    init {
        refreshItems()
    }

    fun refreshItems() {
        viewModelScope.launch {
            val result = repository.getItemsAndSaveToDb()
            if (result!=null) {
                mutableLiveData.postValue(result)
                Log.e("data ", "the data have saved to database")
            } else  {
                Log.e("No Data ", "failed item save to database")
            }
        }
    }
}
