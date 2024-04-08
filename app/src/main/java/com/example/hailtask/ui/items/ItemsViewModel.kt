package com.example.hailtask.ui.items

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class ItemsViewModel @Inject constructor(private val repository: ItemsRepo) : ViewModel() {
    val itemsdata = Pager(PagingConfig(pageSize = 1)) {
        ItemsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    val itemsFromDb: LiveData<PagingData<Item>> = repository.getItemsFromDb().asLiveData()


}

