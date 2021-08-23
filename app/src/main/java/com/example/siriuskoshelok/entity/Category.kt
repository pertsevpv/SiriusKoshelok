package com.example.siriuskoshelok.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerializedName("pictureId")
    var pictureId: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("type")
    var type: Boolean
)