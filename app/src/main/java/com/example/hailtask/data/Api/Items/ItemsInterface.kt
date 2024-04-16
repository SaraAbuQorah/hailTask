package com.example.hailtask.data.Api.Items

import com.example.hailtask.data.model.itemss.GetItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ItemsInterface {
    @GET("get_list?main_category_id=1")
    suspend fun getItems(
        @Header("Authorization") authorization: String,
        @Header("api_key") apiKey: String,
        @Query("page") page: Int?,
        @Query("pageSize") pageSize: Int
        ): Response<GetItems>

}