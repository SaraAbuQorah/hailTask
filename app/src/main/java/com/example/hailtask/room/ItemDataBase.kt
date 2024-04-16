package com.example.hailtask.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.data.model.itemss.RemoteKey

@Database(entities = [Item::class,ItemDetailClass::class,RemoteKey::class], version = 7)
@TypeConverters(Converters::class)
abstract class ItemDataBase : RoomDatabase() {
    abstract fun itemDao(): RoomDao
    abstract fun RemoteKeyDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var instance: ItemDataBase? = null

        fun getDatabase(context: Application): ItemDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDataBase::class.java,
                    "ItemDb"
                ).fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}
