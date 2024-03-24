package com.example.hailtask.data.Api.ItemDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.ui.itemDetails.ItemDetailsViewModel

class ItemDetailsViewModelFactory(private val repository: ItemDeatailsRepo, private val itemId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemDetailsViewModel(repository, itemId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
