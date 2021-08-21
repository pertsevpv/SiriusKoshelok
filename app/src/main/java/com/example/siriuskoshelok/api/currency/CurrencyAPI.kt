package com.example.siriuskoshelok.api.currency

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {

    @GET("fetch-multi?from=RUB&to=USD,EUR,GBP,CNY,CNY,JPY,KRW,CHF&api_key=3792aa04ad-71ca4f9be0-qy6ni9")
    fun getCurrencies(): Call<CurrencyResult>

}