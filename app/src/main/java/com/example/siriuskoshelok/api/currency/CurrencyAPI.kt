package com.example.siriuskoshelok.api.currency

import com.example.siriuskoshelok.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyAPI {

    @GET("fetch-multi?from=RUB&to=USD,EUR,GBP,CNY,CNY,JPY,KRW,CHF&api_key=${Constants.CURR_API_KEY}")
    fun getCurrencies(): Single<CurrencyResult>

}