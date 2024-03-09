package com.example.hailtask.data.Api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.ui.items.ItemsViewModel


@Suppress("UNCHECKED_CAST")
class ItemsMyViewModelFactory(val repository: ItemsRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemsViewModel(repository) as T
    }
}
