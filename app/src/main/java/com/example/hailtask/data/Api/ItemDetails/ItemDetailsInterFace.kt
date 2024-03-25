package com.example.hailtask.data.Api.ItemDetails

import com.example.hailtask.data.model.GetItemDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ItemDetailsInterFace {
    @GET("get_item_details")
     suspend fun getItemDet(@Header("Authorization") authorization: String, @Header("api_key") apiKey: String,@Query("id") id:Int): Response<GetItemDetails>


}