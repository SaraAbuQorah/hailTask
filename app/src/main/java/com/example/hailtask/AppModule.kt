package com.example.hailtask

import android.app.Application
import com.example.hailtask.data.Api.ItemDetails.ItemDetailsInterFace
import com.example.hailtask.data.Api.Items.ItemsInterface
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.room.ItemDataBase
import com.example.hailtask.room.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
     val builder = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS).setLevel(
            HttpLoggingInterceptor.Level.BODY)).connectTimeout(60, TimeUnit.SECONDS).build()
    val BaseUrl = "https://hail.website/api/"

    @Provides
    @Singleton
    fun retrofit(): ItemDetailsInterFace {
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder)
            .build().create(ItemDetailsInterFace::class.java)
    }
    @Provides
    @Singleton
    fun retrofitItems(): ItemsInterface {
       return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
           .client(builder)
            .build().create(ItemsInterface::class.java)
    }


    @Provides
    @Singleton
    fun provideItemDataBase(context: Application): ItemDataBase {
        return ItemDataBase.getDatabase(context)
    }
    @Provides
    @Singleton
    fun provideItemsRepository(apiItem:ItemsInterface,database:ItemDataBase):ItemsRepo{
        return ItemsRepo(database,apiItem)
    }
    @Provides
    @Singleton
    fun provideItemDetailsRepository(api:ItemDetailsInterFace,database:ItemDataBase):ItemDeatailsRepo{
        return ItemDeatailsRepo(database,api)
    }
    @Provides
    @Singleton
    fun RoomDao(database:ItemDataBase):RoomDao{
        return database.itemDao()
    }


}