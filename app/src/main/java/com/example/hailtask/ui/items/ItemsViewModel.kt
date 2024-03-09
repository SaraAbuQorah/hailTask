package com.example.hailtask.ui.items

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.data.model.GetItems
import com.example.hailtask.data.model.Item
import com.example.hailtask.util.Resource
import kotlinx.coroutines.launch

class ItemsViewModel(private val repository: ItemsRepo) : ViewModel(),
    ItemsAdapter.ItemsClickEvents {

     val itemsLiveData = MutableLiveData<Resource<GetItems>>()
    init {
         val auth="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2hhaWwud2Vic2l0ZS9hcGkvbG9naW4iLCJpYXQiOjE3MDk1NDI1NjIsImV4cCI6MTkzMDM4MDk2MiwibmJmIjoxNzA5NTQyNTYyLCJqdGkiOiJST0lsTWVpMUxOUkJubzFsIiwic3ViIjoiNyIsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.5HCuXzsCaW5vqPjp7IsuDUgCdwXfIB9MGj8gjwijZlU"
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
    override fun selected(data: Item) {

    }
}
