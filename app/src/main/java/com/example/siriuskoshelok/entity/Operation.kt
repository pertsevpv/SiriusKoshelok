package com.example.siriuskoshelok.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Operation(
    var img: Int? = null,
    var amount: Int? = null,
    var operationType: String? = null,
    var extendedOperationType: String? = null,
    var date: GregorianCalendar = GregorianCalendar()
): Parcelable
