package com.example.siriuskoshelok.api.currency

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyResult(
    @field:Json(name = "results") val res: Currencies
) : Parcelable

@Parcelize
data class Currencies(
    @field:Json(name = "USD") val usd: Double,
    @field:Json(name = "EUR") val eur: Double,
    @field:Json(name = "GBP") val gbp: Double,
    @field:Json(name = "CNY") val cny: Double,
    @field:Json(name = "JPY") val jpy: Double,
    @field:Json(name = "KRW") val krw: Double,
    @field:Json(name = "CHF") val chf: Double,
) : Parcelable
