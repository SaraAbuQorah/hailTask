package com.example.hailtask.ui.items

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.hailtask.data.Api.Items.ItemsInterface
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.room.ItemDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(private val repository: ItemsRepo,itemDataBase: ItemDataBase,itemsInterface: ItemsInterface ) : ViewModel() {

    @ExperimentalPagingApi
    val itemsFlow: Flow<PagingData<Item>> = repository.getAllNewsStream()


}
