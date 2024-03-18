package com.example.hailtask.data.Api.ItemDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.ui.itemDetails.ItemDetailsViewModel


class ItemDetailsViewModelFactory(val repository: ItemDeatailsRepo):
    ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ItemDetailsViewModel(repository) as T
        }
}