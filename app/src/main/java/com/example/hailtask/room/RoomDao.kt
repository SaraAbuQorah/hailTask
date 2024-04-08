package com.example.hailtask.room

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.data.model.itemss.Item


@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item:List<Item>)

    @Query("Select * From Items")
    fun getItems(): PagingSource<Int, Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemDetails(item: ItemDetailClass)

    @Query("Select * From ItemDetails WHERE id =:id")
    fun getItemDetails(id: Int): LiveData<ItemDetailClass>

}