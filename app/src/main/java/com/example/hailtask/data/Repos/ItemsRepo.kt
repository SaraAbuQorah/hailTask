package com.example.hailtask.data.Repos


import androidx.paging.*
import com.example.hailtask.data.Api.Items.ItemsInterface
import com.example.hailtask.data.Api.Items.RemoteMediatorItems
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.room.ItemDataBase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ItemsRepo @Inject constructor(
    private val itemDatabase: ItemDataBase,
    private val apiItems: ItemsInterface
) {
    @ExperimentalPagingApi
    fun getAllNewsStream(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            remoteMediator = RemoteMediatorItems(database = itemDatabase, networkService = apiItems)

        ) {
            itemDatabase.itemDao().getItems()
        }.flow
    }
}

