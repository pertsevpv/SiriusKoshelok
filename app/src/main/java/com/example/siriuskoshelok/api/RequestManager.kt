package com.example.siriuskoshelok.api

import com.example.siriuskoshelok.api.category.CategoryAPI
import com.example.siriuskoshelok.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object RequestManager {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: CategoryAPI = retrofit.create(CategoryAPI::class.java)
}