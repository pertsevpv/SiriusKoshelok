package com.example.siriuskoshelok.app

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.siriuskoshelok.api.currency.CurrencyAPI
import com.example.siriuskoshelok.database.AppDatabase
import com.example.siriuskoshelok.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class SiriusApplication : Application() {

    lateinit var currApiService: CurrencyAPI
    lateinit var currRetrofit: Retrofit
    lateinit var appDatabase: AppDatabase

    companion object {
        const val CURR_API = "https://api.fastforex.io/"

        lateinit var instance: SiriusApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        currRetrofit = Retrofit.Builder()
            .baseUrl(CURR_API)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        currApiService = currRetrofit.create()

        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            Constants.APP_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

}