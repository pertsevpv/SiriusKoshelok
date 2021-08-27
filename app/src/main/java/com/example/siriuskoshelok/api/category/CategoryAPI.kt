package com.example.siriuskoshelok.api.category

import com.example.siriuskoshelok.data.CurrentUser
import com.example.siriuskoshelok.entity.Category
import io.reactivex.Single
import retrofit2.http.*

interface CategoryAPI {

    @POST("api/categories")
    fun createCategory(
        @Body category: Category,
        @Header("login") login: String = CurrentUser.login ?: ""
    ): Single<Category>
}
