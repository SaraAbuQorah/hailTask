package com.example.hailtask.data.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ItemsHelper {
    companion object {
        val BaseUrl="https://hail.website/api/"
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api by lazy{
            retrofit.create(ItemsInterface::class.java)
        }

    }
}