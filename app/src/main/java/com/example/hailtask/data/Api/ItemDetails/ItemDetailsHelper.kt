package com.example.hailtask.data.Api.ItemDetails

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ItemDetailsHelper {

    companion object {
        private val builder =OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS).setLevel(HttpLoggingInterceptor.Level.BODY)).connectTimeout(60,TimeUnit.SECONDS).build()
        val BaseUrl="https://hail.website/api/"
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder)
                .build()
        }
        val api by lazy{
            retrofit.create(ItemDetailsInterFace::class.java)
        }

    }
}