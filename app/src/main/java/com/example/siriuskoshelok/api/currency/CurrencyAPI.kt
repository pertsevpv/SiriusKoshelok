package com.example.siriuskoshelok.api.currency

import com.example.siriuskoshelok.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {

    @GET("fetch-multi?from=RUB&to=USD,EUR,GBP,CNY,CNY,JPY,KRW,CHF&api_key=${Constants.CURR_API_KEY}")
    fun getCurrencies(): Call<CurrencyResult>

}