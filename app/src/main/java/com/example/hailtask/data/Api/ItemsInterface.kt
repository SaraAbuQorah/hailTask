package com.example.hailtask.data.Api

import com.example.hailtask.data.model.Data
import com.example.hailtask.data.model.GetItems
import com.example.hailtask.data.model.Item
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface ItemsInterface {
    @GET("get_list?main_category_id=1")
            suspend fun getItems( @Header("Authorization") authorization: String) : Response<GetItems>

}