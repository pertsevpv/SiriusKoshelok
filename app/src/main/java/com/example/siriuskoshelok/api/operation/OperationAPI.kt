package com.example.siriuskoshelok.api.operation

import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface OperationAPI {

    @GET("transaction/byID/{id}")
    fun getOperation(@Path("id") id: Int): Observable<Operation>

    @GET("transaction/byWalletID/{walletId}")
    fun getOperations(@Path("walletId") walletId: Int): Observable<MutableList<Operation>>

    @POST("transaction")
    fun postOperation(@Body operation: Operation): Observable<Operation>

    @DELETE("transaction/delete/{id}")
    fun deleteOperation(@Path("id") id: Int): Observable<Operation>

    @PUT("transaction")
    fun updateOperation(@Body operation: Operation): Observable<Operation>

    companion object {
        fun create(): OperationAPI {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(OperationAPI::class.java)
        }
    }
}