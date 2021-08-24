package com.example.siriuskoshelok.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    var pictureId: Int,
    var name: String,
    var type: Boolean
) : Parcelable