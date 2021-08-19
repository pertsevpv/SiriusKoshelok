package com.example.siriuskoshelok.entity

import java.util.*

data class Operation(
    var img: Int?,
    var money: Int?,
    var operationType: String?,
    var extendedOperationType: String?,
    var date: Date = Date()
)