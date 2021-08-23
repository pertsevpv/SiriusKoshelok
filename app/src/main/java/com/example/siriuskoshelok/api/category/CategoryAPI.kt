package com.example.siriuskoshelok.api.category

import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CategoryAPI {

    @GET("category/byID/{id}")
    fun getCategory(@Path("id") id: Int): Observable<Category>

    @POST("category")
    fun postCategory(@Body category: Category): Observable<Category>

    @DELETE("category/delete/{id}")
    fun deleteCategory(@Path("id") id: Int): Observable<Category>

    @PUT("category")
    fun updateCategory(@Body category: Category): Observable<Category>

    companion object {
        fun create(): CategoryAPI {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(CategoryAPI::class.java)
        }
    }
}