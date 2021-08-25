package com.example.siriuskoshelok.api.operation

import com.example.siriuskoshelok.api.category.CategoryAPI
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface OperationAPI {

    @GET("transactions/{id}")
    fun getOperation(@Path("id") id: Int): Single<Operation>

    @GET("wallets/{id}/transactions")
    fun getOperations(@Path("id") id: Int): Single<MutableList<Operation>>

    @POST("transactions")
    fun postOperation(@Body operation: Operation): Single<Operation>

    @DELETE("transactions/delete/{id}")
    fun deleteOperation(@Path("id") id: Int): Single<Operation>

    @PUT("transactions")
    fun updateOperation(@Body operation: Operation): Single<Operation>

    companion object{
        private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: OperationAPI = retrofit.create(OperationAPI::class.java)
    }
}