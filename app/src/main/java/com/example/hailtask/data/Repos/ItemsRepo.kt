    package com.example.hailtask.data.Repos

    import android.app.Application
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.map
    import androidx.paging.Pager
    import androidx.paging.PagingConfig
    import androidx.paging.PagingData
    import com.example.hailtask.data.Api.Items.ItemsInterface
    import com.example.hailtask.data.model.itemss.GetItems
    import com.example.hailtask.data.model.itemss.Item
    import com.example.hailtask.room.ItemDataBase
    import com.example.hailtask.util.Constants
    import androidx.paging.PagingSource
    import kotlinx.coroutines.flow.Flow
    import javax.inject.Inject

    class ItemsRepo @Inject constructor(private val itemDatabase: ItemDataBase,
                                        private val apiItems: ItemsInterface) {

        fun getItemsPagingSource(): PagingSource<Int, Item> {
            return itemDatabase.itemDao().getItems()
        }

        fun getItemsFromDb(): Flow<PagingData<Item>> {
            return Pager(PagingConfig(pageSize = 1)) {
                itemDatabase.itemDao().getItems()
            }.flow
        }

        suspend fun getItemsAndSaveToDb(item: List<Item>) {
            itemDatabase.itemDao().insert(item)
        }

        suspend fun getItems(auth: String, apiKey: String, page: Int, pageSize: Int) =
            apiItems.getItems(auth, apiKey, page, pageSize)
    }
