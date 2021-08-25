package com.example.siriuskoshelok.api.operation

import com.example.siriuskoshelok.entity.Operation
import io.reactivex.Single
import retrofit2.http.*

interface OperationAPI {

    @GET("transaction/byID/{id}")
    fun getOperation(@Path("id") id: Int): Single<Operation>

    @GET("transaction/byWalletID/{walletId}")
    fun getOperations(@Path("walletId") walletId: Int): Single<MutableList<Operation>>

    @POST("transaction")
    fun postOperation(@Body operation: Operation): Single<Operation>

    @DELETE("transaction/delete/{id}")
    fun deleteOperation(@Path("id") id: Int): Single<Operation>

    @PUT("transaction")
    fun updateOperation(@Body operation: Operation): Single<Operation>
}