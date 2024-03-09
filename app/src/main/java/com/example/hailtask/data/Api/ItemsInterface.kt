package com.example.hailtask.data.Api

import com.example.hailtask.data.model.GetItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface ItemsInterface {
    @GET("get_list?main_category_id=1")
    suspend fun getItems(@Header("Authorization") authorization: String,@Header("api_key") apiKey: String): Response<GetItems>

}