package com.example.siriuskoshelok.api.category

import com.example.siriuskoshelok.entity.Category
import retrofit2.Call
import retrofit2.http.*

interface CategoryAPI {

    @GET("byID/{id}")
    fun getCategory(@Path("id") id: Int): Call<Category>

    @POST("category")
    fun postCategory(@Body category: Category): Call<Category>

    @DELETE("delete/{id}")
    fun deleteCategory(@Path("id") id: Int): Call<Category>

    @PUT("category")
    fun updateCategory(@Body category: Category): Call<Category>
}