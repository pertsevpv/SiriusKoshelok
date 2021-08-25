package com.example.siriuskoshelok.api.operations

import com.example.siriuskoshelok.data.CurrentUser
import com.example.siriuskoshelok.entity.Operation
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface OperationAPI {

    @PUT("api/transactions")
    fun editOperation(@Header("login") login: String = CurrentUser.login ?: ""): Completable

    @POST("api/transactions")
    fun postOperation(@Header("login") login: String): Single<Operation>

    @DELETE("api/transactions/delete/{id}")
    fun deleteOperation(@Header("login") login: String, @Path("id") id: Long): Completable

}