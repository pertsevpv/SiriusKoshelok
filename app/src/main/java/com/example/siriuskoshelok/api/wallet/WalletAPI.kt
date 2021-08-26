package com.example.siriuskoshelok.api.wallet

import com.example.siriuskoshelok.data.CurrentUser
import com.example.siriuskoshelok.entity.Operation
import com.example.siriuskoshelok.entity.Wallet
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface WalletAPI {

    @PUT("api/wallets")
    fun editWallet(
        @Body wallet: Wallet,
        @Header("login") login: String = CurrentUser.login ?: ""
    ): Completable

    @POST("api/wallets")
    fun createNewWallet(
        @Body wallet: Wallet,
        @Header("login") login: String = CurrentUser.login ?: ""
    ): Single<Wallet>

    @DELETE("api/wallets/{id}")
    fun deleteWallet(
        @Path("id") id: Long,
        @Header("login") login: String = CurrentUser.login ?: ""
    ): Completable

    @GET("api/wallets/{id}/transactions")
    fun getOperations(
        @Path("id") id: Long,
        @Header("login") login: String = CurrentUser.login ?: ""
    ): Single<List<Operation>>

}