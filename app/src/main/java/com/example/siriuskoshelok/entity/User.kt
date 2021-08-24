package com.example.siriuskoshelok.entity

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "login") var login: String?,
    @field:Json(name = "name") var name: String?,
    @field:Json(name = "surname") var surname: String?,
    @field:Json(name = "registrationDate") var regDate: String?
)