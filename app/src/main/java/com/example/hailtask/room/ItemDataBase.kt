package com.example.hailtask.room

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.data.model.itemss.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDataBase : RoomDatabase() {
    abstract fun itemDao(): RoomDao

    companion object {
        @Volatile
        private var instance: ItemDataBase? = null

        fun getDatabase(context: Context): ItemDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDataBase::class.java,
                    "ItemDb"
                ).build()
            }
            return instance!!
        }
    }
}
