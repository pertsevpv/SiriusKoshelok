package com.example.siriuskoshelok.app

import android.app.Application
import com.example.siriuskoshelok.api.currency.CurrencyAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class SiriusApplication : Application() {

    lateinit var currApiService: CurrencyAPI

    companion object {
        const val CURR_API = "https://api.fastforex.io/"

        lateinit var instance: SiriusApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val currRetrofit : Retrofit = Retrofit.Builder()
            .baseUrl(CURR_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        currApiService = currRetrofit.create()
    }

}