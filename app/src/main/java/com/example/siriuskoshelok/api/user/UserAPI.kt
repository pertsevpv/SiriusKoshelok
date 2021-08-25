package com.example.siriuskoshelok.api.user

import com.example.siriuskoshelok.entity.User
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPI {

    @POST("api/users")
    fun register(@Header("login") login: String, @Body user: User): Completable

}