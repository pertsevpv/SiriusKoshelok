package com.example.siriuskoshelok.api.wallet

import android.os.Parcelable
import com.example.siriuskoshelok.entity.Wallet
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WalletDto(
    @field:Json(name = "walletDtoList") var list: List<Wallet>,
    @field:Json(name = "allProfit") var allProfit: Int,
    @field:Json(name = "allConsumption") var allConsumption: Int,
) : Parcelable