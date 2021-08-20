package com.example.siriuskoshelok.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wallet(
    var name: String? = "",
    var balance: Int = 0,
    val operationList: MutableList<Operation> = mutableListOf(),
    var hasLimit: Boolean = false,
    var limit: Int = Int.MAX_VALUE
):Parcelable