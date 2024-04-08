package com.example.hailtask.ui.items

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.util.Constants
import retrofit2.HttpException
import java.io.IOException

class ItemsPagingSource(private val repository: ItemsRepo) : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 0
        return try {
            val response = repository.getItems(Constants.auth, Constants.apiKey, page, params.loadSize)
            if (response.isSuccessful) {
                val items = response.body()?.data?.items ?: emptyList()
                repository.getItemsAndSaveToDb(items)
                LoadResult.Page(
                    data = items,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (items.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
