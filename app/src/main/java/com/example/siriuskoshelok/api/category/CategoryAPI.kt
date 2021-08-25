package com.example.siriuskoshelok.api.category

import com.example.siriuskoshelok.api.BaseResult
import com.example.siriuskoshelok.entity.Category
import com.example.siriuskoshelok.recycler.items.CategoryItem
import io.reactivex.Single
import retrofit2.http.*

interface CategoryAPI {

    @GET("category/byID/{id}")
    fun getCategory(@Path("id") id: Int): Single<CategoryItem>

    @GET("category")
    fun getCategories(): Single<BaseResult<List<CategoryItem>>>

    @POST("category")
    fun postCategory(@Body category: Category): Single<CategoryItem>

    @DELETE("category/delete/{id}")
    fun deleteCategory(@Path("id") id: Int): Single<CategoryItem>

    @PUT("category")
    fun updateCategory(@Body category: Category): Single<CategoryItem>
}