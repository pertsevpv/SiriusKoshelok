package com.example.siriuskoshelok.api.user

import com.example.siriuskoshelok.api.wallet.WalletDto
import com.example.siriuskoshelok.entity.Wallet
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface UserAPI {

    @GET("api/users/current")
    fun register(@Header("login") login: String): Completable

    @GET("api/users/current/wallets")
    fun getAllWallets(@Header("login") login: String): Single<WalletDto>

}