package com.example.siriuskoshelok.api.category

import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.recycler.items.CategoryItem
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CategoryAPI {

    @GET("categories/{id}")
    fun getCategories(@Path("id") id: Int): Single<List<CategoryItem>>

    @POST("categories")
    fun postCategory(@Body category: Category): Single<CategoryItem>

    @DELETE("categories/{id}")
    fun deleteCategory(@Path("id") id: Int): Single<CategoryItem>

    @PUT("categories")
    fun updateCategory(@Body category: Category): Single<CategoryItem>

    companion object{
        private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: CategoryAPI = retrofit.create(CategoryAPI::class.java)
    }
}