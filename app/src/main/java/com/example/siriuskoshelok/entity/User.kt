package com.example.siriuskoshelok.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @field:Json(name = "login") var login: String?,
    @field:Json(name = "name") var name: String?,
    @field:Json(name = "surname") var surname: String?,
    @field:Json(name = "registrationDate") var regDate: String?
) : Parcelable