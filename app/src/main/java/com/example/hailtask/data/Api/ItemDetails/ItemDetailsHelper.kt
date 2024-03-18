package com.example.hailtask.data.Api.ItemDetails

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ItemDetailsHelper {
    companion object {
        val BaseUrl="https://hail.website/api/"
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api by lazy{
            retrofit.create(ItemDetailsInterFace::class.java)
        }

    }
}